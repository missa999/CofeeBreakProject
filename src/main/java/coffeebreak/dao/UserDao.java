package coffeebreak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import coffeebreak.config.DatabaseConfig;

public class UserDao {
	private static final String URL = "jdbc:mysql://localhost:3306/cafeteria";
    private static final String USER = "root";
    private static final String PASSWORD = "";
	public void createTable() throws SQLException {
	    String sql = "CREATE TABLE IF NOT EXISTS USERS (" +
	                 "ID INT AUTO_INCREMENT PRIMARY KEY," +
	                 "USERNAME VARCHAR(32)," +
	                 "EMAIL VARCHAR(32) UNIQUE," +
	                 "PASSWORD VARCHAR(320)," +
	                 "ADMIN BOOLEAN " +
	                 ")";

	    Connection conn = DatabaseConfig.getInstance().getConnection();
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	    System.out.println("Table USERS créée avec succès.");

	    String checkAdminSql = "SELECT COUNT(*) FROM USERS WHERE ADMIN = TRUE";
	    PreparedStatement checkAdminPstmt = conn.prepareStatement(checkAdminSql);
	    ResultSet rs = checkAdminPstmt.executeQuery();

	    if (rs.next()) {
	        int adminCount = rs.getInt(1);
	        if (adminCount > 0) {
	            return;
	        }
	    }

	    String insertSql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, ADMIN) " +
	                       "VALUES (?, ?, ?, ?)";
	    PreparedStatement insertPstmt = conn.prepareStatement(insertSql);
	    insertPstmt.setString(1, "admin");
	    insertPstmt.setString(2, "admin@gmail.com");
	    insertPstmt.setString(3, "$2a$10$KaodS2Yapmvxe3x5OQGVtexZGtKpFO/.sANlZtpZOceFuCpN1PK5S");
	    insertPstmt.setBoolean(4, true);

	    int rowsAffected = insertPstmt.executeUpdate();
	    if (rowsAffected > 0) {
	        System.out.println("Utilisateur par défaut 'admin' créé avec succès.");
	    } else {
	        System.out.println("Échec de la création de l'utilisateur par défaut.");
	    }
	}
	
	
    public boolean registerUser(String username, String password, String email) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        String sql = "INSERT INTO users (username, password, email, admin) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConfig.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, email);
            stmt.setBoolean(4, false);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String email, String password) {
    	
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
        	Connection conn = null;
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                return BCrypt.checkpw(password, storedHash);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getUserId(String email, String password) {
        String sql = "SELECT ID, PASSWORD FROM USERS WHERE email = ?";
        int userId = -1;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("PASSWORD");
                    if (BCrypt.checkpw(password, storedHash)) {
                        userId = rs.getInt("ID");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }


    public boolean isAdmin(String email) {
    	boolean isAdmin = true;
        String sql = "SELECT admin FROM users WHERE email = ?";
        
        try (
        		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                isAdmin = rs.getBoolean("admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdmin;
    }
    
}
