package coffeebreak.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Product {
    void createTable() throws SQLException;
    void insertProduct(Object product) throws SQLException;
    Object getProductDetails(int id) throws SQLException;
    void updateSales(int id, int newSales) throws SQLException;
    boolean deleteProduct(int id) throws SQLException;
    List<?> getAllProducts() throws SQLException;
    void updateProduct(Object product) throws SQLException;
}
