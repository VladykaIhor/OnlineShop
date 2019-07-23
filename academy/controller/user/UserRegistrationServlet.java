package mate.academy.controller.user;

import mate.academy.factory.UserServiceFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.model.User;
import mate.academy.service.UserService;

@WebServlet({"/register"})
public class UserRegistrationServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    public UserRegistrationServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");
        String role = req.getParameter("role");
        if (password.equals(repeatPassword)) {
            User user = new User(email, login, password, role);
            userService.add(user);
            req.setAttribute("success!", user);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("error", "The repeated passwords aren't equal");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
