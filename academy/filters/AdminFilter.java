package mate.academy.filters;

import mate.academy.model.User;

import javax.servlet.*;
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
