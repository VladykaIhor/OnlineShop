
package controller.product;

import factory.ProductServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ProductService;

@WebServlet({"/delete"})
public class DeleteProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        productService.delete(id);
        resp.sendRedirect("/products");
    }
}
