package com.nnk.springboot.controllers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.GetUserInfoService;
import com.nnk.springboot.services.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

	@Mock
	private UserServiceImpl userService;

	@Mock
	private GetUserInfoService getUserInfoService;

	@Mock
	private Model model;
	
	@Mock
	private Principal user;

	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private UserController userController;

	@Test
	public void homeTest() {
		when(userService.usersList()).thenReturn(new ArrayList<User>());
		String actual = userController.home(model, user);

		assertTrue(actual.equals("user/list"));
	}

	@Test
	public void addUserTest() {
		String actual = userController.addUser(new User());
		assertTrue(actual.equals("user/add"));
	}

	@Test
	public void validateTest_shouldReturnUserList() throws EntityAlreadyExistException {
		User testItem = new User();
		testItem.setPassword("Password@123");
		when(bindingResult.hasErrors()).thenReturn(false);

		String actual = userController.validate(testItem, bindingResult, model);
		assertTrue(actual.equals("redirect:/user/list"));

	}

	@Test
	public void validateTest_WithError_shouldReturnAddPage() throws EntityAlreadyExistException {
		when(bindingResult.hasErrors()).thenReturn(true);

		String actual = userController.validate(new User(), bindingResult, model);
		assertTrue(actual.equals("user/add"));

	}

	@Test
	public void showUpdateFormTest_ShouldReturnUpdate() throws EntityNotFoundException {
		when(userService.getUser(1)).thenReturn(new User());

		String actual = userController.showUpdateForm(1, model);
		assertTrue(actual.equals("user/update"));

	}

	@Test
	public void updateUserTest() throws EntityNotFoundException {
		User testItem = new User();
		when(userService.updateUser(1,testItem)).thenReturn(new User());

		String actual = userController.updateUser(1, testItem, bindingResult, model);
		assertTrue(actual.equals("redirect:/user/list"));

	}

	@Test
	public void deleteUserTest() throws EntityNotFoundException {

		String actual = userController.deleteUser(1, model);

		assertTrue(actual.equals("redirect:/user/list"));

	}
}
