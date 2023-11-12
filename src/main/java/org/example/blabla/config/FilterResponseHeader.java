package org.example.blabla.config;

import org.example.blabla.domain.pojo.UserPojo;
import org.example.blabla.util.AuthUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {
        "/user/*",
        "/location/*",
        "/order/*"
})
public class FilterResponseHeader implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        Optional.of(AuthUtil.getUserFromContext())
                .map(UserPojo::getToken)
                .ifPresent((token) -> {
                    var httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.setHeader("Authorization", token.toString());
                });
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}