package com.boot.application.controller.rest;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.application.security.JwtTokenUtils;
import com.boot.domain.entity.account.Login;
import com.boot.domain.entity.account.User;


/**
 * 
 * @author Eduardo
 * 
 * Rest controller utilizada para realização de login de um usuário
 *
 */
@RestController
@RequestMapping("/api/user")
public class AuthRestController {

	private static final Logger logger = Logger.getLogger(AuthRestController.class.getName());
	
	/**
	 * Para realizar a validação do login e senha do usuário
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * Para gerenciar o token
	 */
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	/**
	 * Realiza a autenticação de um usuário através da api
	 * 
	 * @param login
	 * @param httpServletResponse
	 * @return usuário autenticado com seu token de acesso
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping(
		value = "/login", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity loginApi(
		@Valid @RequestBody Login login, 
		HttpServletResponse httpServletResponse
	) {
		
		logger.info("AuthRestController.loginApi() by: "+login.getLogin());
		
		UsernamePasswordAuthenticationToken authenticationToken = 
		new UsernamePasswordAuthenticationToken(
			login.getLogin(), 
			login.getPassword()
		);
		
		try {
			
			Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
			// seta pessoa autenticada na sessão
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String token = tokenUtils.createToken(login.getLogin());
			
			User pessoaLogada = (User) authentication.getPrincipal();
			
			Assert.notNull(pessoaLogada, "Login ou senha inválidos");
			
			pessoaLogada.setTokenJwt(token);
			
			System.out.println(token);
			
			return new ResponseEntity<User>(pessoaLogada, HttpStatus.OK);
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	
}
