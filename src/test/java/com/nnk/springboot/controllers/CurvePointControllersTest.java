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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.CurvePointServiceImpl;
import com.nnk.springboot.services.GetUserInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurvePointControllersTest {

	@Mock
	private CurvePointServiceImpl curvePointService;
	
	@Mock
	private GetUserInfoService getUserInfoService;

	@Mock
	private Model model;
	
	@Mock
	private Principal user;

	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private CurveController curveController;

	@Test
	public void homeTest() {
		when(curvePointService.curvePointList()).thenReturn(new ArrayList<CurvePoint>());
		String actual = curveController.home(model, user);
		assertTrue(actual.equals("curvePoint/list"));
	}

	@Test
	public void addCurveFormTest() {
		String actual = curveController.addCurveForm(new CurvePoint());
		assertTrue(actual.equals("curvePoint/add"));
	}

	@Test
	public void validateTest_ShouldReturnBidList() throws EntityAlreadyExistException {
		when(bindingResult.hasErrors()).thenReturn(false);
		String actual = curveController.validate(new CurvePoint(), bindingResult, model);
		assertTrue(actual.equals("redirect:/curvePoint/list"));
	}

	@Test
	public void validateTest_withError_shouldReturnAddPage() throws EntityAlreadyExistException {
		when(bindingResult.hasErrors()).thenReturn(true);
		String actual = curveController.validate(new CurvePoint(), bindingResult, model);
		assertTrue(actual.equals("curvePoint/add"));
	}

	@Test
	public void showUpdateFormTest_ShouldReturnUpdate() throws EntityNotFoundException {
		when(curvePointService.getCurvePoint(1)).thenReturn(new CurvePoint());
		String actual = curveController.showUpdateForm(1, model);
		assertTrue(actual.equals("curvePoint/update"));
	}

	@Test
	public void updateCurvePointTest() throws EntityNotFoundException {
		CurvePoint testItem = new CurvePoint();
		when(curvePointService.updateCurvePoint(1, testItem)).thenReturn(testItem);
		String actual = curveController.updateCurvePoint(1, testItem, bindingResult, model);
		assertTrue(actual.equals("redirect:/curvePoint/list"));
	}

	@Test
	public void test_deleteBid() throws EntityNotFoundException {
		String actual = curveController.deleteCurvePoint(1, model);
		assertTrue(actual.equals("redirect:/curvePoint/list"));
	}

}
