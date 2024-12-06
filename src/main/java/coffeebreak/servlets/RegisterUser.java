package coffeebreak.servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffeebreak.dao.UserDao;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        request.setAttribute("username", username);
        request.setAttribute("email", email);
        if (!isValidUsername(username)) {
            request.setAttribute("errorMessage", "Invalid username. It must be at least 3 characters long.");
            request.getRequestDispatcher("user/register.jsp").forward(request, response);
            return;
        }

        if (!isValidEmail(email)) {
            request.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("user/register.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(password)) {
            request.setAttribute("errorMessage", "Invalid password. It must be at least 8 characters long and contain a mix of letters and numbers.");
            request.getRequestDispatcher("user/register.jsp").forward(request, response);
            return;
        }


        UserDao userDAO = new UserDao();
        boolean isRegistered = userDAO.registerUser(username, password, email);

        if (isRegistered) {
            response.sendRedirect("user/login.jsp");
        } else {
            request.setAttribute("errorMessage", "Registration failed! email already exist.");
            request.getRequestDispatcher("user/register.jsp").forward(request, response);
        }
    }

    private boolean isValidUsername(String username) {
        return username != null && username.length() >= 3;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return password != null && Pattern.matches(passwordRegex, password);
    }
}
