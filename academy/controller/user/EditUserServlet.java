package mate.academy.controller.user;

import mate.academy.factory.UserServiceFactory;
import mate.academy.model.User;
import mate.academy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/edit/users"})

public class EditUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        req.setAttribute("id", id);
        User user = userService.getById(id).get();
        req.setAttribute("oldLogin", user.getLogin());
        req.setAttribute("oldPassword", user.getPassword());
        req.getRequestDispatcher("/change_user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Optional<User> optUser = userService.getById(id);
            if (optUser.isPresent()) {
                User user = optUser.get();
                user.setLogin(login);
                user.setPassword(password);
                req.getRequestDispatcher("/users.jsp").forward(req, resp);
            }
        } catch (NumberFormatException ex) {
            req.setAttribute("validValues", "Something is wrong. Try again.");
            req.getRequestDispatcher("/edit/users").forward(req, resp);
        }

    }
}
