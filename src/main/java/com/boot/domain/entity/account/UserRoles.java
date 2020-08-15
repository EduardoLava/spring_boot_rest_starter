package com.boot.domain.entity.account;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {

//	--------------------------------------------
//				enums
//	--------------------------------------------
	
	ROLE_ADMIN,
	ROLE_USER;

//	--------------------------------------------
//				BEHAVIORS
//	--------------------------------------------
	
	@Override
	public String getAuthority() {
		return this.name();
	}
	
}
