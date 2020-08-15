package com.boot.domain.entity.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Eduardo 
 * 
 * Bean utilizado somente para realização de login por meio da api
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor()
public class Login {

	/**
	 * Login do usuário 
	 */
	@NotBlank
	private String login;
	
	/**
	 * Senha do usuário
	 */
	@NotBlank
	@Size(max=120)
	private String password;
	
}
