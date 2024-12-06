package coffeebreak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import coffeebreak.config.DatabaseConfig;
import coffeebreak.interfaces.CartCalculationStrategy;
import coffeebreak.models.Order;

public class CartDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/cafeteria";
    private static final String USER = "root";
    private static final String PASSWORD = "";
	private List<Order> orders;

	public CartDAO(List<Order> orders) {
		super();
		this.orders = orders;
	}

	public CartDAO() {
		super();
	}
	public void viderList() {
		orders.removeAll(orders);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public double calculateTotal(CartCalculationStrategy strategy) {
		return strategy.calculateTotal(this);
	}

	public void createOrderTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS ORDERS (" + "ID INT AUTO_INCREMENT PRIMARY KEY,"
				+ "NOM_CAFE VARCHAR(32) NOT NULL," + "QUANTITE INT NOT NULL," + "PRIX_TOTAL FLOAT NOT NULL,"
				+ "DATE_COMMANDE TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + "ID_USER INT" + // Ajout du champ ID_USER sans
																							// clé étrangère
				")";

		Connection conn = DatabaseConfig.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try {
			pstmt.executeUpdate();
			System.out.println("Table ORDERS créée avec succès.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la création de la table ORDERS.");
		}
	}

	public void InsertCart(List<Order> orders, int id_user) throws SQLException {
		
		 String sql = "INSERT INTO ORDERS (NOM_CAFE, QUANTITE, PRIX_TOTAL, ID_USER) VALUES (?, ?, ?, ?)";
		    
		    Connection conn = null;
		    PreparedStatement pstmt = null;

		    try {
		        // Get the connection once
		        conn = DriverManager.getConnection(URL, USER, PASSWORD);
		        pstmt = conn.prepareStatement(sql);

		        for (Order order : orders) {
		            pstmt.setString(1, order.getProductName());
		            pstmt.setInt(2, order.getQuantity());
		            pstmt.setDouble(3, order.getSubtotal());
		            pstmt.setInt(4, id_user);
		            pstmt.executeUpdate();
		            System.out.println("Order inserted successfully for: " + order.getProductName());
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("Error while inserting orders.");
		    } 
	}
	
	public List<Order> ajoutAuPannier(List<Order> orders, Order order) {
	    // Check if the order already exists in the list based on productName
	    for (Order existingOrder : orders) {
	        if (existingOrder.getProductName().equals(order.getProductName())) {
	            // If order exists, increment its quantity
	            existingOrder.setQuantity(existingOrder.getQuantity() + order.getQuantity());
	            return orders;
	        }
	    }
	    
	    // If order doesn't exist, add it to the list
	    orders.add(order);
	    return orders;
	}


}
