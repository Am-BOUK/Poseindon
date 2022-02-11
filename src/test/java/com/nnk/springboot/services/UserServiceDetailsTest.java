package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceDetailsTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceDetailsImpl userServiceDetails;

	@Test
	public void loadUserByUsernameTest() {
		User user = new User("UserName", "password", "Full Name", "ROLE");
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findByUsername("UserName")).thenReturn(optionalUser);

		UserDetails result = userServiceDetails.loadUserByUsername("UserName");

		assertEquals(result.getUsername(), "UserName");
	}

	@Test
	public void loadUserByUsernameTest_whenUsernameDoesNotExist() {
		try {
			userServiceDetails.loadUserByUsername("UserName");

		} catch (Exception e) {
			assertTrue(e instanceof Exception);
			assertThat(e.getMessage()).contains("User : UserName, not found!");
		}

	}

}
