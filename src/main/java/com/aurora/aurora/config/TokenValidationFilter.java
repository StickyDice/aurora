package com.aurora.aurora.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aurora.aurora.utils.JwtUtil;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenValidationFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(@SuppressWarnings("null") @NonNull HttpServletRequest request,
      @SuppressWarnings("null") @NonNull HttpServletResponse response,
      @SuppressWarnings("null") @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    String path = request.getRequestURI();
    boolean isRefreshRequest = path.startsWith("/api/auth/");
    boolean isSwaggerRequest = path.contains("swagger") || path.contains("api-docs");
    String token = request.getHeader("Authorization");

    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
      try {
        if (!validateToken(token)) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.getWriter().write("Invalid token 1");
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
          return;
        }
      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(e.getLocalizedMessage());
        return;
      }
    } else if (!isRefreshRequest && !isSwaggerRequest) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Invalid token 3");
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
      return;
    }

    filterChain.doFilter(request, response);
  }

  private boolean validateToken(String token) {
    return jwtUtil.validateAccessToken(token);
  }
}
