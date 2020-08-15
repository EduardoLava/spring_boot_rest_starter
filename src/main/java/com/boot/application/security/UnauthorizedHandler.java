package com.boot.application.security;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Eduardo
 * 
 * Handler padrão para quando o usuário for inválido
 *
 */
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {

	private static final Logger logger = java.util.logging.Logger.getLogger(UnauthorizedHandler.class.getName());

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws java.io.IOException, ServletException {
		
		logger.severe("Unauthorized error: {}"+ authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
		
	}

}
