package coffeebreak.config;

import coffeebreak.dao.CartDAO;
import coffeebreak.interfaces.CartCalculationStrategy;
import coffeebreak.models.Order; 

public class StandardCalculation implements CartCalculationStrategy {

	@Override
    public double calculateTotal(CartDAO cart) {
        return cart.getOrders().stream()
                   .mapToDouble(order -> order.getPrice() * order.getQuantity())
                   .sum();
    }
}
