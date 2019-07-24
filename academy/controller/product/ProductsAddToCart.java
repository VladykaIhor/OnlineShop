package mate.academy.controller.product;

import mate.academy.dao.impl.ProductDaoImpl;
import mate.academy.factory.CartServiceFactory;
import mate.academy.factory.ProductServiceFactory;
import mate.academy.model.Product;
import mate.academy.model.User;
import mate.academy.service.CartService;
import mate.academy.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/users/add_to_cart")
public class ProductsAddToCart extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);
    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final CartService cartService = CartServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> currentUser = Optional.ofNullable((User)
                request.getSession().getAttribute("user"));
        if (currentUser.isPresent()) {
            String id = request.getParameter("id");
            Optional<Product> additionalProduct = Optional.ofNullable
                    (productService.getById(Long.parseLong(id)));
            if (additionalProduct.isPresent()) {
                LOGGER.info("Try to add " + additionalProduct.get()
                        + "to "
                        + currentUser.get() + " cart... \n");
                cartService.addCartProduct(additionalProduct.get());
                response.sendRedirect("/users/products");
            } else {
                LOGGER.info("Product is not found  \n" + id);
            }
        } else {
            request.setAttribute("message", "Please, sign in");
            request.getRequestDispatcher("/store.jsp");
            LOGGER.info("User not found  \n");
        }
    }
}
