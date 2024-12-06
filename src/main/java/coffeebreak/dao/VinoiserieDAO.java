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
import coffeebreak.models.Vinoiserie;

public class VinoiserieDAO implements Product {
	private static final String URL = "jdbc:mysql://localhost:3306/cafeteria";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    @Override
    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS VINOISERIES (" +
                     "ID INT AUTO_INCREMENT PRIMARY KEY," +
                     "NOM VARCHAR(32)," +
                     "PRIX FLOAT," +
                     "VENTES INT," +
                     "IMAGE_URL VARCHAR(255)" +
                     ")";
        Connection conn = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("Table VINOISERIES créée avec succès.");

    }

    @Override
    public void insertProduct(Object product) throws SQLException {
        if (product instanceof Vinoiserie) {
            Vinoiserie vinoiserie = (Vinoiserie) product;
            String sql = "INSERT INTO VINOISERIES (NOM, PRIX, VENTES, IMAGE_URL) VALUES (?, ?, ?, ?)";
            Connection conn = DatabaseConfig.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, vinoiserie.getNom());
                pstmt.setFloat(2, vinoiserie.getPrix());
                pstmt.setInt(3,vinoiserie.getVentes());
                pstmt.setString(4, vinoiserie.getImageUrl());
                pstmt.executeUpdate();
                System.out.println("Vinoiserie insérée : " + vinoiserie.getNom());
        }
    }

    @Override
    public Vinoiserie getProductDetails(int id) throws SQLException {
        String sql = "SELECT * FROM VINOISERIES WHERE ID = ?";
        Connection conn = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Vinoiserie(rs.getInt("ID"),
                		rs.getString("NOM"),
                		rs.getFloat("PRIX"),
                		rs.getInt("VENTES"),
                        rs.getString("IMAGE_URL")
                        );
            }
        return null;
    }

    @Override
    public void updateSales(int id, int newSales) throws SQLException {
        String sql = "UPDATE VINOISERIES SET VENTES = ? WHERE ID = ?";
        Connection conn = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newSales);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Ventes mises à jour pour la vinoiserie avec ID : " + id);
    }

    @Override
    public void updateProduct(Object product) throws SQLException {
        if (product instanceof Vinoiserie) {
        	Vinoiserie vinoiserie = (Vinoiserie) product;
            String sql = "UPDATE VINOISERIES SET NOM = ?, PRIX = ?, VENTES = ? WHERE ID = ?";
            Connection conn = DatabaseConfig.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vinoiserie.getNom());
            pstmt.setFloat(2, vinoiserie.getPrix());
            pstmt.setInt(3, vinoiserie.getVentes());
            pstmt.setInt(4, vinoiserie.getId());  // Use the ID of the cafe to identify the record to update

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Café mis à jour : " + vinoiserie.getNom());
            } else {
                System.out.println("Aucun café trouvé avec l'ID : " + vinoiserie.getId());
            }
        }
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM VINOISERIES WHERE ID = ?";
        Connection conn = DatabaseConfig.getInstance().getConnection();
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
    public List<Vinoiserie> getAllProducts() throws SQLException {
        List<Vinoiserie> vinoiseries = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD); // Open connection
            String sql = "SELECT * FROM vinoiseries";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
            	Vinoiserie v = new Vinoiserie();
                v.setId(rs.getInt("ID"));

                v.setNom(rs.getString("NOM"));
                v.setPrix(rs.getFloat("PRIX"));
                v.setVentes(rs.getInt("VENTES"));
                v.setImageUrl((rs.getString("IMAGE_URL")));
                vinoiseries.add(v);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vinoiseries;
    }

}
