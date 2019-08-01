package controller.user;

import factory.UserServiceFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserService;

@WebServlet({"/admin/users"})
public class AllUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    public AllUserServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> allUsers = userService.getAll();
        req.setAttribute("allUsers", allUsers);
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> optUser = userService.findUserByLogin(login);
        if (optUser.isPresent() && password.equals(userService.findUserByLogin(login)
                .get().getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", optUser.get());
            resp.sendRedirect("users.jsp");
            resp.sendRedirect("/admin/users");
        } else {
            req.setAttribute("login", login);
            req.setAttribute("isValid", "Username or password is not correct");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }
}


