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
import java.util.Optional;

@WebServlet({"/remove/users"})
public class DeleteUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    public DeleteUserServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<User> userToBeRemoved = userService.getById(id);
        userService.remove(userToBeRemoved);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/admin/users");
        }
}
