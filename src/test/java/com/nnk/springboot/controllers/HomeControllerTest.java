package com.nnk.springboot.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import com.nnk.springboot.services.GetUserInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
class HomeControllerTest {

	@Mock
	private GetUserInfoService getUserInfoService;

	@Mock
	private Model model;

	@Mock
	private Principal user;

	@InjectMocks
	private HomeController homeController;

	@Test
	public void homeTest() {
		String actual = homeController.home(model, user);
		assertTrue(actual.equals("/home"));

	}

	@Test
	public void adminHomeTest() {
		String actual = homeController.adminHome();
		assertTrue(actual.equals("redirect:/bidList/list"));
	}

}
