package org.example.blabla.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CustomFilter extends GenericFilterBean {

    private final SecurityService securityService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        securityService.isUserAuthenticated(request.getServletContext().get);

        chain.doFilter(request, response);
    }
}