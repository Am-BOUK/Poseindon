package com.nnk.springboot.services;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * implementation of UserServiceDetails business processing
 * 
 */
@Service
public class UserServiceDetailsImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * loadUserByUsername ** this operation allows find user by his username then to
	 * load user details information (username, password and authority) if he exists
	 * 
	 * @param username : the username of the user who wants to authenticate
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.get().getRole());
			return new org.springframework.security.core.userdetails.User(username, user.get().getPassword(),
					Collections.singletonList(grantedAuthority));
		} else {
			throw new InternalAuthenticationServiceException("User : " + username + ", not found!");
		}
	}
}
