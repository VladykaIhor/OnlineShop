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
        User testAdmin = new User("test@test", "test", "test", "admin");
        User testUser = new User("asd@asd", "asd", "asd", "user");
        userService.add(testUser);
        userService.add(testAdmin);
        Product firstProduct = new Product("Earphones", "closed-type", 100.0);
        Product product2 = new Product("Guitar pick", "1,00mm", 2.0);
        Product product3 = new Product("Audio cable", "jack 3,5mm", 100.0);
        productService.add(product1);
        productService.add(product2);
        productService.add(product3);
    }
}
