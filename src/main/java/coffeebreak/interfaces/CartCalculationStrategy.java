package coffeebreak.interfaces;

import coffeebreak.dao.CartDAO;

public interface CartCalculationStrategy {
	
	public double calculateTotal(CartDAO cart);
}
