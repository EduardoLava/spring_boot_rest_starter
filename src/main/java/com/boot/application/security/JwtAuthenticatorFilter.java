package com.boot.application.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.boot.domain.service.UserService;


/**
 * 
 * @author Eduardo
 * 
 * Filter responsável pela validação do token nos requests
 *
 */
public class JwtAuthenticatorFilter extends OncePerRequestFilter {

	private static final Logger logger = Logger.getLogger(JwtAuthenticatorFilter.class.getName()); 
	
	/**
	 * Para validar o token
	 */
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	/**
	 * Para realizar autenticação 
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * Método responsável por filtrar os requests identificando o usuário que está tentando fazer algum request
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
		HttpServletResponse response, 
		FilterChain filterChain
	) throws ServletException, IOException {

		logger.info("JwtAuthenticatorFilter.doFilterInternal()");
		
		try {
			String jwt = resolveToken(request);
			
			if (tokenUtils.validateJwtToken(jwt)) {
				String username = tokenUtils.getUserNameFromJwtToken(jwt);

				UserDetails userDetails = userService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			logger.severe("Cannot set user authentication: {}" + e);
		}

		filterChain.doFilter(request, response);
		
	}
	
	/**
	 * 
	 * @param request
	 * @return token enviado no requst através do header Authorization 
	 */
	private String resolveToken(HttpServletRequest request) {
		
		logger.info("JwtAuthenticatorFilter.resolveToken()");
		
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}

}
