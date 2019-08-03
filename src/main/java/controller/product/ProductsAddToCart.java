package controller.product;

import factory.CartServiceFactory;
import factory.ProductServiceFactory;
import model.Cart;
import model.Product;
import model.User;
import service.CartService;
import service.ProductService;
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

    private static final Logger LOGGER = Logger.getLogger(ProductsAddToCart.class);
    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final CartService cartService = CartServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<User> currentUser = Optional.ofNullable((User)
                request.getSession().getAttribute("user"));
        String id = request.getParameter("id");
        Optional<Product> product = productService.getById(Long.parseLong(id));
        if (cartService.getCartByUser(currentUser.get()).isPresent()) {
            Optional<Cart> cartByUser = cartService.getCartByUser(currentUser.get());
        } else {
            cartService.createCart(currentUser.get());
        }
        cartService.addProductToCart(currentUser.get(), product.get());
        Cart cart = cartService.getCartByUser(currentUser.get()).get();
        int sizeOfACart = cartService.getSizeOfACart(cart);
        request.setAttribute("count", sizeOfACart);
        request.setAttribute("products", productService.getAll());
        request.getRequestDispatcher("/products_user.jsp").forward(request, response);
        //  response.sendRedirect("/users/products");
    }
}

