//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mate.academy.controller.product;

import mate.academy.factory.ProductServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.model.Product;
import mate.academy.service.ProductService;

@WebServlet({"/products/edit"})
public class EditProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    public EditProductsServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = productService.getById(id);
        req.setAttribute("id", product.getId());
        req.setAttribute("oldName", product.getName());
        req.setAttribute("oldDescription", product.getDescription());
        req.setAttribute("oldPrice", product.getPrice());
        req.getRequestDispatcher("/change_product.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Product productToEditWith = new Product(id, name, description, price);
        productService.update(productToEditWith);
        resp.sendRedirect("/products");
    }
}
