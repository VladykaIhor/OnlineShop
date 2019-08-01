package controller.product;

import factory.CartServiceFactory;
import factory.CodeServiceFactory;
import factory.MailServiceFactory;
import factory.OrderServiceFactory;
import model.Code;
import model.Order;
import model.Product;
import model.User;
import service.CartService;
import service.CodeService;
import service.MailService;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
