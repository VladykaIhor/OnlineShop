package controller.user;

import factory.UserServiceFactory;
import model.Product;
import model.User;
import service.UserService;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> optionalUser = userService.findUserByLogin(login);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", optionalUser.get());
            if (optionalUser.get().getRole().equals("admin")) {
                resp.sendRedirect("admin/users");
            } else {
                req.getRequestDispatcher("/users/products").forward(req, resp);
            }
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {



    }
}
