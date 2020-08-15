package com.boot.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.domain.entity.account.User;
import com.boot.domain.repository.account.IUserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(
		String username
	) throws UsernameNotFoundException {
		
		User user = userRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha inválidos"));
		
		return user;
	}

	
}
