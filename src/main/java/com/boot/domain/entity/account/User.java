package com.boot.domain.entity.account;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.boot.domain.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@NoArgsConstructor()
public class User extends BaseEntity implements UserDetails  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1771517964817680720L;

	// ----------------------------------------------
	// ------------------ attributes ----------------
	// ----------------------------------------------
	
	@Column(nullable = false)
	@NotBlank
	private String name;
	
	@Column(nullable = false, unique=true)
	@NotBlank
	private String login;
	
	@JsonIgnore
	@Column(nullable = false)
	@NotBlank
	@Size(max=120)
	private String password;
	
	@JsonProperty(access= Access.READ_ONLY)
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@ElementCollection(fetch= FetchType.EAGER)
	@CollectionTable()
	@Column(nullable= false)
	private Set<UserRoles> roles;
	
	@Column(nullable=false)
	private Boolean isActive;

	@Transient
	private String tokenJwt;
	
//	----------------------------------------------
//	---------------- behaviors ------------------------------
//	----------------------------------------------
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Transient
	@Override
	public String getUsername() {
		return login;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return this.isActive;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return this.isActive;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return this.isActive;
	}
	
}
