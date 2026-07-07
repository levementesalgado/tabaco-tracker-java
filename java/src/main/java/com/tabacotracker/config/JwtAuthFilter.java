package com.tabacotracker.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;

@Component @Order(1) @RequiredArgsConstructor
class JwtAuthFilter implements Filter {
    final JwtUtil jwtUtil;
    static final List<String> PUBLIC = List.of("/api/v1/health", "/api/v1/leaderboard", "/api/v1/ranking");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        if (PUBLIC.stream().anyMatch(path::startsWith) || path.equals("/") || path.startsWith("/swagger")) {
            chain.doFilter(request, response); return;
        }

        String auth = req.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Bearer ")) {
            ((HttpServletResponse) response).sendError(401, "Missing token"); return;
        }

        UUID userId = jwtUtil.validateToken(auth);
        if (userId == null) {
            ((HttpServletResponse) response).sendError(401, "Invalid token"); return;
        }

        req.setAttribute("userId", userId);
        chain.doFilter(request, response);
    }
}
