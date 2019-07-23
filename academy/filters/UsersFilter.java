package mate.academy.filters;

import mate.academy.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/users/*")
public class UsersFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        User userFromSession = (User) httpServletRequest.getSession().getAttribute("user");
        if (userFromSession != null && !userFromSession.getRole().isEmpty()) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect("/");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
