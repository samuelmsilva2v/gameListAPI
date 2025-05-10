package com.example.demo.infrastructure.filters;

import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends GenericFilterBean {

	private final String secretKey = "9658011f-94ec-49e6-8602-f517a94fc6bb";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		var httpRequest = (HttpServletRequest) request;
		var httpResponse = (HttpServletResponse) response;

		String authHeader = httpRequest.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado.");
			return;
		}

		try {
			String token = authHeader.substring(7);
			Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			httpRequest.setAttribute("claims", claims);
			chain.doFilter(request, response);
		} catch (Exception e) {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado.");
		}
	}
}
