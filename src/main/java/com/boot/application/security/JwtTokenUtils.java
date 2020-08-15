package com.boot.application.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boot.application.config.AppConfig;

/**
 * 
 * @author Eduardo
 * 
 * Classe responsável pela criação e validação de um token
 *
 */
@Component
public class JwtTokenUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);
	
	@Autowired
	private AppConfig appConfig;
	
	private Key key;

	/**
	 * Gera uma key para posterior criação dos tokens
	 */
	private Key getKey(){
		if(key == null){
			key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		}
		return key;
	}
	
	/**
	 * Cria um token com base na chave que está configurada no yml propriedade
	 * Secret e com base no tempo de validade do token, também configurado lá,
	 * na propriedade token-validity-in-seconds
	 * 
	 * @param username
	 * @return
	 */
	public String createToken(String username) {
		
		System.out.println("JwtTokenUtils.createToken()");
		
		Date now = new java.util.Date();
		Date validity = new Date(now.getTime() + (1000 * appConfig.getTokenValidityInSeconds()));

		return Jwts.builder().setId(UUID.randomUUID().toString())
				.setSubject(username)
				// setIssuedAt recebe a data atual
				.setIssuedAt(now)
//				.signWith(alg, base64EncodedSecretKey)
				.signWith(getKey())
				// recebe a chave e o algoritmo
//				.signWith(SignatureAlgorithm.HS512, this.secretKey)
				// data de validade do token
				.setExpiration(validity).compact();
	}
	
	/**
	 * 
	 * Busca o nome do usuário presente no token
	 * 
	 * @param token
	 * @return nome do usuário
	 */
	public String getUserNameFromJwtToken(String token) {
		
		System.out.println("JwtTokenUtils.getUserNameFromJwtToken()");
		
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	/**
	 * Valida o token de acordo com a assinatura gerada 
	 * 
	 * @param authToken
	 * @return
	 */
	public boolean validateJwtToken(String authToken) {
		
		System.out.println("JwtTokenUtils.validateJwtToken()");
		
		try {
			Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(authToken);
			return true;
		} catch (SecurityException e) {
			logger.error("Invalid signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("Token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("Token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("Claims string is empty: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("Error {}", e.getMessage());
		}

		return false;
	}
	
}
