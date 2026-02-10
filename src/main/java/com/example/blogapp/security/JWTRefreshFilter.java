package com.example.blogapp.security;

import com.example.blogapp.repository.UserRegisterEntityRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTRefreshFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserRegisterEntityRepository userRepo; // Needed to check if user still exists

    public JWTRefreshFilter(JWTUtil jwtUtil, UserRegisterEntityRepository userRepo) {
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if (!request.getServletPath().equals("/refresh-token")) {
            filterChain.doFilter(request, response);
            return;
        }
        String refreshToken = extractJwtFromRequest(request);
        if (refreshToken == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 3. Validate Token directly (Fixes ProviderNotFoundException)
        String username = jwtUtil.validateAndExtractUsername(refreshToken);

        if (username != null && userRepo.findByUsername(username).isPresent()) {
            // 4. Generate New Access Token
            // valid for 15 minutes
            String newToken = jwtUtil.generateToken(username, 15);
            // 5. Send Response
            response.setContentType("application/json");
            response.setHeader("Authorization", "Bearer " + newToken); // Fixed space typo
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired refresh token");
        }
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        String refreshToken = null;
        for (Cookie cookie : cookies) {
            if ("refreshToken".equals(cookie.getName())) {
                refreshToken = cookie.getValue();
            }
        }
        return refreshToken;
    }
}
