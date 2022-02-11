package com.nnk.springboot.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	public void gettingLoginPageTest_ShouldReturnDefaultMessage() throws Exception {
		mock.perform(get("/login")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void userLoginTest_ShouldReturnAuthenticated_WhenUserExists() throws Exception {
		mock.perform(formLogin("/login").user("amal").password("Amal@123")).andExpect(authenticated());
	}

	@Test
	public void userLoginTest__WhenUserDoesNotExists_ShouldFail() throws Exception {
		mock.perform(formLogin("/login").user("unknown").password("wrongPassport")).andExpect(unauthenticated());
	}

}
