package coffeebreak.servlets;

import coffeebreak.dao.CafeDAO;
import coffeebreak.dao.VinoiserieDAO;
import coffeebreak.factory.ProductFactory;
import coffeebreak.interfaces.Product;
import coffeebreak.models.Cafe;
import coffeebreak.models.Vinoiserie;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MenuServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product productDAO = ProductFactory.getProductDAO("cafe");
            Product productDAOV = ProductFactory.getProductDAO("vinoiserie");

            List<?> products = productDAO.getAllProducts();
            List<?> productsV = productDAOV.getAllProducts();

            request.setAttribute("products", products);
            request.setAttribute("productsV", productsV);

            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
