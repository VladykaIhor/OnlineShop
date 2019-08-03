package controller.product;

import factory.CodeServiceFactory;
import factory.OrderServiceFactory;
import model.Code;
import model.User;
import service.CodeService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

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
        Optional<Code> code = codeService.getCode(orderIdByUser);
        if (code.equals(confirm)) {
            req.setAttribute("message", "It works!");
        } else {
            req.setAttribute("message", "It doesn't work");
        }
        req.getRequestDispatcher("/order_confirmation.jsp").forward(req, resp);
    }
}
