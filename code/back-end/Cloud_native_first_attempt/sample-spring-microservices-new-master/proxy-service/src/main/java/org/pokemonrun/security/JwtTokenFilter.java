package org.pokemonrun.security;

import io.jsonwebtoken.JwtException;
import org.pokemonrun.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token != null) {
            if (!jwtTokenProvider.isTokenPresentInDB(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid JWT token");
                throw new CustomException("Invalid JWT token", HttpStatus.UNAUTHORIZED);
            }
            try {
                jwtTokenProvider.validateToken(token) ;
            } catch (JwtException | IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid JWT token");
                throw new CustomException("Invalid JWT token", HttpStatus.UNAUTHORIZED);
            }
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(req, res);

    }
}
