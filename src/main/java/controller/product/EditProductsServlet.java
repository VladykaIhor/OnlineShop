package controller.product;

import factory.ProductServiceFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.ProductService;
import org.apache.log4j.Logger;

@WebServlet({"/products/edit"})
public class EditProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final Logger logger = Logger.getLogger(EditProductsServlet.class);

    public EditProductsServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Optional<Product> product = productService.getById(id);
        req.setAttribute("id", product.get().getId());
        req.setAttribute("oldName", product.get().getName());
        req.setAttribute("oldDescription", product.get().getDescription());
        req.setAttribute("oldPrice", product.get().getPrice());
        req.getRequestDispatcher("/change_product.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        if (isNullOrEmpty(name) || isNullOrEmpty(description) || isNullOrEmpty(String.valueOf(price))) {
            req.setAttribute("id", id);
            req.setAttribute("incompleteFormError", "The form is not fully completed!");
            req.setAttribute("oldName", name);
            req.setAttribute("oldDescription", description);
            req.setAttribute("oldPrice", price);
            req.getRequestDispatcher("/edit-product.jsp").forward(req, resp);
        } else {
            if (price <= 0) {
                logger.info("The price can't be lower than two");
                resp.sendRedirect("/products");
            }
            Product newProduct = new Product(name, description, price);
            Optional<Product> productOptional = productService.getById(id);
            productOptional.ifPresent(product -> productService.update(product, newProduct));
            resp.sendRedirect("/products");
        }
    }

    private boolean isNullOrEmpty(String requestParameter) {
        return Objects.isNull(requestParameter) || requestParameter.trim().isEmpty();
    }
}
