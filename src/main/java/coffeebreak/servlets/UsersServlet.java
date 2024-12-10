package coffeebreak.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coffeebreak.dao.UserDao;

@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        boolean isAuthenticated = userDao.authenticateUser(email, password);
        int id_user=userDao.getUserId(email, password);
        
        if (isAuthenticated) {
            boolean isAdmin = userDao.isAdmin(email);

            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            
            System.out.println("id_user : "+id_user);
            
            session.setAttribute("id_user", id_user);
            
            if (isAdmin) {
                response.sendRedirect("user/home.jsp");
            } else {
                response.sendRedirect("MenuServlet");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("user/login.jsp").forward(request, response);
        }
    }
}
