package com.boot.domain.entity.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor()
public class Login {

	@NotBlank
	private String login;
	
	@NotBlank
	@Size(max=120)
	private String password;
	
}
