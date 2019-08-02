package mate.academy.controller.product;

import mate.academy.factory.CartServiceFactory;
import mate.academy.factory.CodeServiceFactory;
import mate.academy.factory.MailServiceFactory;
import mate.academy.factory.OrderServiceFactory;
import mate.academy.model.Code;
import mate.academy.model.Order;
import mate.academy.model.Product;
import mate.academy.model.User;
import mate.academy.service.CartService;
import mate.academy.service.CodeService;
import mate.academy.service.MailService;
import mate.academy.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/users/order_confirmation"})
public class OrderInformationServlet extends HttpServlet {

    private static final CartService cartService = CartServiceFactory.getInstance();
    private static final MailService mailService = MailServiceFactory.getInstance();
    private static final CodeService codeService = CodeServiceFactory.getInstance();
    private static final OrderService orderService = OrderServiceFactory.getInstance();


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> cartProducts = cartService.getCartProducts();
        req.setAttribute("cartProducts", cartProducts);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(
                "/order_confirmation.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        User user = (User) req.getSession().getAttribute("user");
        Order order = new Order(req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("email"),
                req.getParameter("country"),
                req.getParameter("state"),
                req.getParameter("city"),
                req.getParameter("phoneNumber"),
                req.getParameter("zip"));
        req.getSession().setAttribute("order", order);
        Long orderIdByUser = orderService.getOrderIdByUser(user);
        Code code = new Code(orderIdByUser);
        codeService.addCode(code);
        mailService.sendConfirmCode(user.getEmail(), code.getCode());
        req.getRequestDispatcher("/orderPayment.jsp").forward(req, resp);
    }
}
