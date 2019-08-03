package controller.user;

import factory.UserServiceFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;

import static utils.SaltHashingUtil.getSalt;
import static utils.SaltHashingUtil.saltAndHashPassword;

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
            User user = new User(login, email, password, role, getSalt());
            user.setPassword(saltAndHashPassword(user.getPassword(), user.getSalt()));
            userService.add(user);
            req.setAttribute("success!", user);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("error", "The repeated passwords aren't equal");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
