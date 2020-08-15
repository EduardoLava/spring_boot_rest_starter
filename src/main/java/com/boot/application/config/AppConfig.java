package com.boot.application.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Para importar as configs do yamll
 * 
 * @author Eduardo
 *
 */
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	/**
	 * Tempo de expiração do token de redefinição de senha.
	 */
	@Getter
	@Setter
	private long tokenValidityInSeconds;
	
}
