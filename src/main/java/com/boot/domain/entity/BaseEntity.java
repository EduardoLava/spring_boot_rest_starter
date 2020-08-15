package com.boot.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Edu
 * base para todas as entidades
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDateTime created;
	
	@NotNull
	private LocalDateTime updated;
	
}
