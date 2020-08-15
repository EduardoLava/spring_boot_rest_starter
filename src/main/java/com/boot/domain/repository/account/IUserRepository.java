package com.boot.domain.repository.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.domain.entity.account.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String login);
	
}
