package controller.user;

import factory.UserServiceFactory;
import model.User;
import service.UserService;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static utils.SaltHashingUtil.saltAndHashPassword;

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
                user.setPassword(saltAndHashPassword(password, user.getSalt()));
                userService.updateUser(userService.getById(id).get(), user);
                resp.sendRedirect("/admin/users");
            }
        } catch (NumberFormatException ex) {
            req.setAttribute("validValues", "Something is wrong. Try again.");
            req.getRequestDispatcher("/admin/users").forward(req, resp);
        }

    }
}
