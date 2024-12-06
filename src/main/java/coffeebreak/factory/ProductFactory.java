package coffeebreak.factory;

import coffeebreak.dao.CafeDAO;
import coffeebreak.dao.VinoiserieDAO;
import coffeebreak.interfaces.Product;

public class ProductFactory {
    public static Product getProductDAO(String productType) {
        if ("cafe".equalsIgnoreCase(productType)) {
            return new CafeDAO();
        } else if ("vinoiserie".equalsIgnoreCase(productType)) {
            return new VinoiserieDAO();
        }
        throw new IllegalArgumentException("Invalid product type: " + productType);
    }
}
