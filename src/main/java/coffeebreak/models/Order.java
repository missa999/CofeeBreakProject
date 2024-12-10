package coffeebreak.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import coffeebreak.config.DatabaseConfig;
import coffeebreak.interfaces.Observer;
import coffeebreak.interfaces.Subject;

public class Order implements Comparable<Order>,Subject {
	private String productName;
	private double price;
	private int quantity;
    private List<Observer> observers = new ArrayList<>();

	public Order(String productName, double price, int quantity) {
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	public Order() {
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return price * quantity;
	}

	@Override
	public int compareTo(Order o) {
		return this.productName.compareTo(o.getProductName());
	}
	
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        try {
            Connection connection = DatabaseConfig.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT email FROM users WHERE email = ?");
            ps.setString(1, "admin@gmail.com");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                attach(new coffeebreak.models.User(rs.getString("email")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Observer observer : observers) {
            observer.update(message);
        }
    }
	
}
