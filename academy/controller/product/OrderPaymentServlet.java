package mate.academy.controller.product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/payment")
public class OrderPaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = (String) req.getSession().getAttribute("code");
        String confirm = req.getParameter("confCode");
        if (code.equals(confirm)) {
            req.setAttribute("message", "Don't worry go to sleep :)");
        } else {
            req.setAttribute("message", "Invalid code, try again");
        }
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}
