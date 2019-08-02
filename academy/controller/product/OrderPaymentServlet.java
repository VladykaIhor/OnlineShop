package mate.academy.controller.product;

import mate.academy.factory.CodeServiceFactory;
import mate.academy.factory.OrderServiceFactory;
import mate.academy.model.User;
import mate.academy.service.CodeService;
import mate.academy.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/payment")
public class OrderPaymentServlet extends HttpServlet {

    private static final CodeService codeService = CodeServiceFactory.getInstance();
    private static final OrderService orderService = OrderServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String confirm = req.getParameter("confCode");
        Long orderIdByUser = orderService.getOrderIdByUser(user);
        String code = codeService.getCode(orderIdByUser);
        if (code.equals(confirm)) {
            req.setAttribute("message", "Don't worry go to sleep :)");
        } else {
            req.setAttribute("message", "Invalid code, try again");
        }
        req.getRequestDispatcher("/order_confirmation.jsp").forward(req, resp);
    }
}
