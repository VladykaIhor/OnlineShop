package mate.academy.controller.user;

import mate.academy.factory.UserServiceFactory;
import mate.academy.model.User;
import mate.academy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
}
