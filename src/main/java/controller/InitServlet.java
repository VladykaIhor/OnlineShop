package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.User;
import service.ProductService;
import service.UserService;
import static utils.SaltHashingUtil.getSalt;

@WebServlet(value = {"/"}, loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() {
        User testAdmin = new User("test", "test@test", "test", "admin", getSalt());
        User testUser = new User("asd", "asd@asd", "asd", "user", getSalt());
        User testUser2 = new User("vasya", "vasya@vasya", "asd", "user", getSalt());
        userService.add(testUser);
        userService.add(testUser2);
        userService.add(testAdmin);
        Product product1 = new Product("Guitar", "A red one", 1531.3);
        Product product2 = new Product("Podspichechnik", "Fioletowy", 100.3);
        Product product3 = new Product("Shlyapa", "Gay", 154.3);
        Product product4 = new Product("Blok gvozdej", "Blue", 120.3);
        productService.add(product1);
        productService.add(product2);
        productService.add(product3);
        productService.add(product4);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login").forward(req, resp);
    }
}
