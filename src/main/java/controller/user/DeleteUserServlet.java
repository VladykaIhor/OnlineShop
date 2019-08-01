package controller.user;

import factory.UserServiceFactory;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin/remove/users"})
public class DeleteUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    public DeleteUserServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        userService.remove(userId);
        resp.sendRedirect("/admin/users");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
