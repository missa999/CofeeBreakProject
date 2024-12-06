package coffeebreak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coffeebreak.config.DatabaseConfig;
import coffeebreak.interfaces.Product;
import coffeebreak.models.Cafe;

public class CafeDAO implements Product {
	
	private static final String URL = "jdbc:mysql://localhost:3306/cafeteria";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS CAFES (" +
                     "ID INT AUTO_INCREMENT PRIMARY KEY," +
                     "NOM_CAFE VARCHAR(32)," +
                     "PRIX FLOAT," +
                     "VENTES INT," +
                     "IMAGE_URL VARCHAR(255)" +
                     ")";
        Connection conn = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("Table CAFES créée avec succès.");
    }

    @Override
    public void insertProduct(Object product) throws SQLException {
        if (product instanceof Cafe) {
            Cafe cafe = (Cafe) product;
            String sql = "INSERT INTO CAFES (NOM_CAFE, PRIX, VENTES, IMAGE_URL) VALUES (?, ?, ?, ?)";
            Connection conn = DatabaseConfig.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cafe.getNomCafe());
            pstmt.setFloat(2, cafe.getPrix());
            pstmt.setInt(3, cafe.getVentes());
            pstmt.setString(4, cafe.getImageUrl()); // Save the image URL
            pstmt.executeUpdate();
            System.out.println("Café inséré : " + cafe.getNomCafe());
        }
    }


    @Override
    public Cafe getProductDetails(int id) throws SQLException {
        String sql = "SELECT * FROM CAFES WHERE ID = ?";
        Connection conn = conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Cafe(
                rs.getInt("ID"),
                rs.getString("NOM_CAFE"),
                rs.getFloat("PRIX"),
                rs.getInt("VENTES"),
                rs.getString("IMAGE_URL") // Retrieve the image URL
            );
        }
        return null;
    }


    @Override
    public void updateSales(int foId, int newSales) throws SQLException {
        String sql = "UPDATE CAFES SET VENTES = ? WHERE ID = ?";
        Connection conn = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newSales);
            pstmt.setInt(2, foId);
            pstmt.executeUpdate();
            System.out.println("Ventes mises à jour pour le café avec ID : " + foId);
    }

    @Override
    public void updateProduct(Object product) throws SQLException {
        if (product instanceof Cafe) {
            Cafe cafe = (Cafe) product;
            String sql = "UPDATE CAFES SET NOM_CAFE = ?, PRIX = ?, VENTES = ? WHERE ID = ?";
            Connection conn = conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cafe.getNomCafe());
            pstmt.setFloat(2, cafe.getPrix());
            pstmt.setInt(3, cafe.getVentes());
            pstmt.setInt(4, cafe.getId());  // Use the ID of the cafe to identify the record to update

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Café mis à jour : " + cafe.getNomCafe());
            } else {
                System.out.println("Aucun café trouvé avec l'ID : " + cafe.getId());
            }
        }
    }


    @Override
    public boolean deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM CAFES WHERE ID = ?";
        Connection conn = conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Café supprimé avec succès : " + id);
            return true;
        } else {
            System.out.println("Erreur lors de la suppression du café avec ID : " + id);
            return false;
        }
    }


    @Override
    public List<Cafe> getAllProducts() {
        List<Cafe> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD); // Open connection
            String sql = "SELECT * FROM cafeS";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cafe cafe = new Cafe();
                cafe.setId(rs.getInt("ID"));
                cafe.setNomCafe(rs.getString("NOM_CAFE"));
                cafe.setPrix(rs.getFloat("PRIX"));
                cafe.setVentes(rs.getInt("VENTES"));
                cafe.setImageUrl((rs.getString("IMAGE_URL")));
                products.add(cafe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}
