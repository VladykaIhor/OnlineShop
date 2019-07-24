package mate.academy.controller.product;

import mate.academy.factory.ProductServiceFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.model.Product;
import mate.academy.service.ProductService;

@WebServlet({"/products"})
public class ProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    public ProductsServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.getAll();
        req.setAttribute("products", productList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("products.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.valueOf(req.getParameter("price"));
        if (isNullOrEmpty(name) || isNullOrEmpty(description) || isNullOrEmpty(String.valueOf(price))) {
            req.setAttribute("Invalid", "Please fill all the fields");
            req.getRequestDispatcher("/products");
        } else {
            Product product = new Product(name, description, price);
            productService.add(product);
        }
        this.doGet(req, resp);
    }


    private boolean isNullOrEmpty(String requestParameter) {
        return Objects.isNull(requestParameter) || requestParameter.trim().isEmpty();
    }

}
