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

public class JwtBearerFilter extends GenericFilterBean {

	private static final String SECRET_KEY = "9658011f-94ec-49e6-8602-f517a94fc6bb";

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			filterChain.doFilter(request, response);
			return;
		}

		final String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado.");
			return;
		}

		try {
			final String token = authHeader.substring(7);
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
			String role = claims.get("role", String.class);
			request.setAttribute("claims", claims);

			String path = request.getRequestURI();
			String method = request.getMethod();

			boolean isGameEndpoint = path.startsWith("/api/games");

			boolean isGetAllGames = isGameEndpoint && "GET".equals(method);
			boolean isGetGameById = isGameEndpoint && path.matches("^/api/games/[^/]+$") && "GET".equals(method);

			if (isGameEndpoint && !(isGetAllGames || isGetGameById) && !"ADMIN".equals(role)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Apenas administradores podem modificar jogos.");
				return;
			}

			filterChain.doFilter(request, response);

		} 
		catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado.");
		}
	}
}
