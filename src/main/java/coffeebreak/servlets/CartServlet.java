package coffeebreak.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffeebreak.dao.CartDAO;
import coffeebreak.models.Order;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartDAO oder;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		oder = new CartDAO(new ArrayList<>());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("product", oder.getOrders());
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("checkout");
		String idUserParam = request.getParameter("id_user");
		if (action != null && !action.isEmpty() && idUserParam != null && !idUserParam.isEmpty()) {
		    try {
		        int id_user = Integer.parseInt(idUserParam); 
		        for (Order order : oder.getOrders()) {
			        Order ordre = new Order(order.getProductName(),order.getSubtotal(),order.getQuantity());
			        ordre.notifyObservers("A new order has been added: "+ order.getProductName() + ", "+order.getSubtotal()+ ", "+ order.getQuantity() + ", user ID: "+ id_user);
		        }
		        oder.InsertCart(oder.getOrders(), id_user);
		        oder.viderList();
		        response.sendRedirect("MenuServlet");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } catch (NumberFormatException e) {
		        e.printStackTrace();
		        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format.");
		    }
		} else {
			String productName = request.getParameter("productName");
			double price = Double.parseDouble(request.getParameter("price"));
			oder.ajoutAuPannier(oder.getOrders(), new Order(productName, price, 1));
			request.setAttribute("product", oder.getOrders());
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}

	}

}
