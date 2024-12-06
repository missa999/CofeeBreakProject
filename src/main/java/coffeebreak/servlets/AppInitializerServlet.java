package coffeebreak.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffeebreak.dao.CartDAO;
import coffeebreak.dao.UserDao;
import coffeebreak.factory.ProductFactory;
import coffeebreak.interfaces.Product;

@WebServlet(name = "AppInitializerServlet", urlPatterns = "/init", loadOnStartup = 1)
public class AppInitializerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            Product cafeDAO = ProductFactory.getProductDAO("cafe");
            cafeDAO.createTable();
            System.out.println("Table CAFES initialized.");

            Product vinoiserieDAO = ProductFactory.getProductDAO("vinoiserie");
            vinoiserieDAO.createTable();
            System.out.println("Table VINOISERIES initialized.");

	        UserDao userDAO = new UserDao();
	        userDAO.createTable();
            System.out.println("Table USERS initialized.");
            CartDAO cart = new CartDAO();
	        cart.createOrderTable();;
            System.out.println("Table orders initialized.");
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize tables", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendRedirect("/coffeebreak/user/login.jsp");
    }
}
