package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	public void getUserTest() throws EntityNotFoundException {
		User user = new User("UserName", "password", "Full Name", "ROLE");
		user.setId(1);
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(1)).thenReturn(optionalUser);
		User result = userService.getUser(1);

		assertEquals(result.getId(), 1);
	}

	@Test
	public void getUserTest_whenUserDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			userService.getUser(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The user : 1, you want to get, does not exist!");
		}

	}

	@Test
	public void addNewUserTest() throws EntityAlreadyExistException {
		User user = new User("UserName", "password", "Full Name", "ROLE");
		when(userRepository.save(user)).thenReturn(user);

		User result = userService.addNewUser(user);
		assertEquals(result.getUsername(), "UserName");

	}

	@Test
	public void addNewUserTest_whenUserAlreadyExist_shouldReturnEntityAlreadyExistException() {
		User user = new User("UserName", "password", "Full Name", "Role");
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findByUsername("UserName")).thenReturn(optionalUser);
		try {
			userService.addNewUser(user);
		} catch (EntityAlreadyExistException e) {
			assertTrue(e instanceof EntityAlreadyExistException);
			assertThat(e.getMessage()).contains("The username : UserName, is already exists!");
		}

	}

	@Test
	public void updateUserTest() throws EntityNotFoundException {
		User user = new User("UserName", "password", "Full Name", "ROLE");
		user.setId(1);
		Optional<User> optionalUser = Optional.of(user);
		User userUpdated = new User();
		userUpdated.setRole("ROLEUpdated");
		when(userRepository.findById(1)).thenReturn(optionalUser);
		when(userRepository.saveAndFlush(user)).thenReturn(user);

		User result = userService.updateUser(1, userUpdated);
		assertEquals(result.getRole(), "ROLEUpdated");

	}

	@Test
	public void updateUserTest_whenUserDoesNotExist_shouldReturnEntityNotFoundException() {
		User userUpdated = new User();
		userUpdated.setUsername("UserNameUpdated");
		try {
			userService.updateUser(1, userUpdated);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The user id : 1, you want to update, does not exist!");
		}

	}

	@Test
	public void userListTest() {
		User user = new User("UserName", "password", "Full Name", "ROLE");
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(userRepository.findAll()).thenReturn(userList);

		List<User> result = userService.usersList();
		assertThat(result.contains(user));
	}

	@Test
	public void deleteUserTest() throws EntityNotFoundException {
		User user = new User("UserName", "password", "Full Name", "ROLE");
		user.setId(1);
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(1)).thenReturn(optionalUser);

		boolean resultat = userService.deleteUser(1);
		
		assertTrue(resultat);

	}

	@Test
	public void deleteUserTest_whenUserDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			userService.deleteUser(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The user id : 1, you want to delete, does not exist!");
		}

	}

}
