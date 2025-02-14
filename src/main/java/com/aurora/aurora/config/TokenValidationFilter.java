package com.aurora.aurora.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

  private final Map<String, Boolean> pathWithAuth = new HashMap<String, Boolean>();

  @Override
  protected void doFilterInternal(@SuppressWarnings("null") @NonNull HttpServletRequest request,
      @SuppressWarnings("null") @NonNull HttpServletResponse response,
      @SuppressWarnings("null") @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    boolean acceptRequest = filterPath(request, response, filterChain);

    if (acceptRequest) {
      filterChain.doFilter(request, response);
    }
  }

  private boolean validateToken(String token) {
    return jwtUtil.validateAccessToken(token);
  }

  private boolean validateIfPathRequiresToken(String path) {
    for (String key : pathWithAuth.keySet()) {
      if (path.startsWith(key)) {
        return true;
      }
    }
    return false;
  }

  private boolean filterPath(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    String path = request.getRequestURI();
    Boolean pathRequiresAuth = validateIfPathRequiresToken(path);

    System.out.println("path " + path + " " + pathRequiresAuth);

    if (!pathRequiresAuth)
      return true;

    String token = request.getHeader("Authorization");

    if (token == null || !token.startsWith("Bearer "))
      return false;

    token = token.substring(7);
    try {
      if (!validateToken(token)) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid token 1");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
        return false;
      }

      return true;
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write(e.getLocalizedMessage());
      return false;
    }
  }
}
