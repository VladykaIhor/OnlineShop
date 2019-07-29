package mate.academy.controller;

import mate.academy.factory.ProductServiceFactory;
import mate.academy.factory.UserServiceFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.model.Product;
import mate.academy.model.User;
import mate.academy.service.ProductService;
import mate.academy.service.UserService;
import mate.academy.utils.IdCounterUtil;

@WebServlet(value = {"/"}, loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    public InitServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login").forward(req, resp);
    }

    @Override
    public void init() {

    }
}
