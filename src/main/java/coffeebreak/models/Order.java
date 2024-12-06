package coffeebreak.models;

public class Order implements Comparable<Order> {
	private String productName;
	private double price;
	private int quantity;

	public Order(String productName, double price, int quantity) {
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
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
	
}
