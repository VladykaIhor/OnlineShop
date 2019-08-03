package filters;

import model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        User userFromSession = (User) httpServletRequest.getSession().getAttribute("user");
        if (userFromSession != null & userFromSession.getRole().equals("admin")) {
            chain.doFilter(req, resp);
        } else {
            httpServletResponse.sendRedirect("/users/products");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
