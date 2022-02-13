package com.nnk.springboot.services;

import static org.junit.Assert.assertTrue;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetUserInfoTest {

	@Autowired
	private GetUserInfoService userInfoService;

	@Mock
	Principal principal;

	@Test
	public void test_getUsernamePasswordLoginInfo_IsAuthenticated_ShouldReturnUserName() {

		UserDetails user = User.withUsername("Amal").password("Pass@123").authorities("USER").build();

		ArrayList<GrantedAuthority> itemList = new ArrayList<>();
		itemList.add((new SimpleGrantedAuthority("USER")));

		UsernamePasswordAuthenticationToken item = new UsernamePasswordAuthenticationToken(user, null, itemList);

		principal = item;
		StringBuffer actual = userInfoService.getUsernamePasswordLoginInfo(principal);
		assertTrue(actual.toString().equals("Username : Amal"));

	}

	@Test
	public void getUsernamePasswordLoginInfo_ifNotAuthenticated_shouldReturnNA() {
		UserDetails user = User.withUsername("Amal").password("Pass@123").authorities("USER").build();
		principal = new UsernamePasswordAuthenticationToken(user, null);

		StringBuffer actual = userInfoService.getUsernamePasswordLoginInfo(principal);

		assertTrue(actual.toString().equals("NA"));
	}

	@Test
	public void test_getOauth2LoginInfo_isAuthenticatedWithGithub_ShouldReturnName() {
		Set<GrantedAuthority> itemList = new HashSet<>();
		itemList.add((new SimpleGrantedAuthority("USER")));

		Map<String, Object> loginTest = new HashMap<>();
		loginTest.put("login", "Amal");

		OAuth2User fortheTest = new DefaultOAuth2User(itemList, loginTest, "login");
		OAuth2AuthenticationToken itemTesting = new OAuth2AuthenticationToken(fortheTest, itemList, "test");

		principal = itemTesting;
		StringBuffer actual = userInfoService.getOauth2LoginInfo(principal);
		assertTrue(actual.toString().equals("Github login : Amal"));
	}

	@Test
	public void test_getOauth2LoginInfo_IfNotAuthenticated_ShouldReturnNA() {

		Set<GrantedAuthority> itemList = new HashSet<>();
		itemList.add((new SimpleGrantedAuthority("USER")));

		Map<String, Object> loginTest = new HashMap<>();
		loginTest.put("login", "Amal");

		OAuth2User fortheTest = new DefaultOAuth2User(itemList, loginTest, "login");

		OAuth2AuthenticationToken itemTesting = new OAuth2AuthenticationToken(fortheTest, itemList, "test");
		itemTesting.setAuthenticated(false);

		principal = itemTesting;

		StringBuffer actual = userInfoService.getOauth2LoginInfo(principal);

		assertTrue(actual.toString().equals("NA"));
	}

}
