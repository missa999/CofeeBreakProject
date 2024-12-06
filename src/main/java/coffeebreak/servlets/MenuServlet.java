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
            // Get DAO instances for Cafe and Vinoiserie
            Product productDAO = ProductFactory.getProductDAO("cafe");
            Product productDAOV = ProductFactory.getProductDAO("vinoiserie");

            // Get the list of products
            List<?> products = productDAO.getAllProducts();
            List<?> productsV = productDAOV.getAllProducts();

            // Log the product lists for debugging
            System.out.println("Cafe products: " + products);
            System.out.println("Vinoiserie products: " + productsV);

            // Set the products in the request scope for the JSP to access
            request.setAttribute("products", products);
            request.setAttribute("productsV", productsV);

            // Forward the request to the menu.jsp page
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally send an error response in case of an issue
            // response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
