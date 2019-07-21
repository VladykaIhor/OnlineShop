package mate.academy.controller.product;

import mate.academy.factory.CartServiceFactory;
import mate.academy.factory.OrderServiceFactory;
import mate.academy.model.Code;
import mate.academy.model.User;
import mate.academy.service.CartService;
import mate.academy.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet({"/users/order_confirmation"})
public class CartServlet extends HttpServlet {
    private static final CartService cartService = CartServiceFactory.getInstance();
    private static final OrderService orderService = OrderServiceFactory.getInstance();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> currentUser = Optional.ofNullable((User)
                request.getSession().getAttribute("user"));

        if (currentUser.isPresent()) {
            if (cartService.getCartProducts().isPresent()) {
                request.setAttribute("flag", 1);
                request.setAttribute("order", cartService.getCartProducts().get());
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Empty cart :(");
                request.setAttribute("flag", 0);
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Sign in, please");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> currentUser = Optional.ofNullable((User)
                request.getSession().getAttribute("user"));

        if (cartService.getCartProducts().isPresent()) {
            String deliveryAddress = request.getParameter("delivery");
            String email = request.getParameter("email");
            if (deliveryAddress.isEmpty() || email.isEmpty()) {
                request.setAttribute("error", "Empty fields");
                request.setAttribute("flag", 1);
                request.setAttribute("order", cartService.getCartProducts().get());
                request.getRequestDispatcher("/products_user.jsp").forward(request, response);
            } else {
                cartService.createNewOrder(currentUser.get(),
                        deliveryAddress,
                        cartService.getCartProducts().get());
                Code code = cartService.sendConfirmationCode(email);
                HttpSession session = request.getSession();
                session.setAttribute("code", code.getCode());
                request.getRequestDispatcher("/orderPayment.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "The cart is empty");
            request.setAttribute("flag", 0);
            request.getRequestDispatcher("/products_user.jsp").forward(request, response);
        }
    }
}
