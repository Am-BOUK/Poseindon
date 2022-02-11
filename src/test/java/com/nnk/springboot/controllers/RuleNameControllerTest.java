package com.nnk.springboot.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.GetUserInfoService;
import com.nnk.springboot.services.RuleNameServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class RuleNameControllerTest {

	@Mock
	private RuleNameServiceImpl ruleNameService;

	@Mock
	private GetUserInfoService getUserInfoService;

	@Mock
	private Model model;
	
	@Mock
	private Principal user;

	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private RuleNameController ruleNameController;

	@Test
	public void homeTest() {
		when(ruleNameService.ruleNameList()).thenReturn(new ArrayList<RuleName>());
		String actual = ruleNameController.home(model, user);
		assertTrue(actual.equals("ruleName/list"));
	}

	@Test
	public void addRuleNameFormTest() {
		String actual = ruleNameController.addRuleForm(new RuleName());
		assertTrue(actual.equals("ruleName/add"));

	}

	@Test
	public void validateTest_shouldReturnRuleNameList() {
		when(bindingResult.hasErrors()).thenReturn(false);
		String actual = ruleNameController.validate(new RuleName(), bindingResult, model);
		assertTrue(actual.equals("redirect:/ruleName/list"));
	}

	@Test
	public void validateTest_withError_shouldReturnAddPage() {
		when(bindingResult.hasErrors()).thenReturn(true);
		String actual = ruleNameController.validate(new RuleName(), bindingResult, model);
		assertTrue(actual.equals("ruleName/add"));

	}

	@Test
	public void showUpdateFormTest_shouldReturnUpdate() throws EntityNotFoundException {
		when(ruleNameService.getRuleName(1)).thenReturn(new RuleName());
		String actual = ruleNameController.showUpdateForm(1, model);
		assertTrue(actual.equals("ruleName/update"));
	}

	@Test
	public void updateRuleNameTest() throws EntityNotFoundException {
		RuleName testItem = new RuleName();
		when(ruleNameService.updateRuleName(1, testItem)).thenReturn(testItem);
		String actual = ruleNameController.updateRuleName(1, testItem, bindingResult, model);
		assertTrue(actual.equals("redirect:/ruleName/list"));
	}

	@Test
	public void deleteRuleNameTest() throws EntityNotFoundException {
		String actual = ruleNameController.deleteRuleName(1, model);
		assertTrue(actual.equals("redirect:/ruleName/list"));
	}

}
