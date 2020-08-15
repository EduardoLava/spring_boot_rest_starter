package com.boot.domain.repository.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.domain.entity.account.User;

/**
 * 
 * @author Eduardo
 * 
 * Reposotório para operações na base de dados para um usuário
 *
 */
public interface IUserRepository extends JpaRepository<User, Long> {
	/**
	 * 
	 * Faz uma busca de usuário através do campo login
	 * 
	 * @param login
	 * @return
	 */
	Optional<User> findByLogin(String login);
	
}
