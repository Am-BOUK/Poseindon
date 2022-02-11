package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;

@ExtendWith(MockitoExtension.class)
class CurvePointServiceTest {

	@Mock
	private CurvePointRepository curvePointRepository;

	@InjectMocks
	private CurvePointServiceImpl curvePointService;

	@Test
	public void getCurvePointTest() throws EntityNotFoundException {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		curvePoint.setId(1);
		Optional<CurvePoint> optionalCurvePoint = Optional.of(curvePoint);
		when(curvePointRepository.findById(1)).thenReturn(optionalCurvePoint);
		CurvePoint result = curvePointService.getCurvePoint(1);

		assertEquals(result.getId(), 1);
	}

	@Test
	public void getCurvePointTest_whenCurvePointDoesNotExist_shouldReturnEntityNotFoundException() {

		try {
			curvePointService.getCurvePoint(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The curvePoint id : 1, you want to get, does not exist!");
		}

	}

	@Test
	public void addNewCurvePointTest() throws EntityAlreadyExistException {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

		CurvePoint result = curvePointService.addCurvePoint(curvePoint);
		assertEquals(result.getCurveId(), 10);

	}

	@Test
	public void addNewCurvePointTest_whenCurvePointAlreadyExist_shouldReturnEntityAlreadyExistException() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		Optional<CurvePoint> optionalCurvePoint = Optional.of(curvePoint);
		when(curvePointRepository.findByCurveId(10)).thenReturn(optionalCurvePoint);
		try {
			curvePointService.addCurvePoint(curvePoint);
		} catch (EntityAlreadyExistException e) {
			assertTrue(e instanceof EntityAlreadyExistException);
			assertThat(e.getMessage()).contains("The curve id : 10, you want to add, is already exists!");
		}
	}

	@Test
	public void updateCurvePointTest() throws EntityNotFoundException {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		curvePoint.setId(1);
		Optional<CurvePoint> optionalCurvePointList = Optional.of(curvePoint);
		CurvePoint curvePointUpdated = new CurvePoint();
		curvePointUpdated.setValue(20d);
		when(curvePointRepository.findById(1)).thenReturn(optionalCurvePointList);
		when(curvePointRepository.saveAndFlush(curvePoint)).thenReturn(curvePoint);

		CurvePoint result = curvePointService.updateCurvePoint(1, curvePointUpdated);
		assertEquals(result.getValue(), 20d);

	}

	@Test
	public void updateCurvePointTest_whenCurvePointDoesNotExist_shouldReturnEntityNotFoundException() {
		CurvePoint curvePointUpdated = new CurvePoint();
		curvePointUpdated.setValue(20d);
		try {
			curvePointService.updateCurvePoint(1, curvePointUpdated);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The curve point id : 1, you want to update, does not exist!");
		}

	}

	@Test
	public void curvePointListTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		List<CurvePoint> curvePointList = new ArrayList<>();
		curvePointList.add(curvePoint);
		when(curvePointRepository.findAll()).thenReturn(curvePointList);

		List<CurvePoint> result = curvePointService.curvePointList();
		assertThat(result.contains(curvePoint));
	}

	@Test
	public void deleteCurvePointListTest() throws EntityNotFoundException {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		curvePoint.setId(1);
		Optional<CurvePoint> optionalCurvePoint = Optional.of(curvePoint);
		when(curvePointRepository.findById(1)).thenReturn(optionalCurvePoint);

		boolean resultat = curvePointService.deleteCurvePoint(1);

		assertTrue(resultat);

	}

	@Test
	public void deleteCurvePointListTest_whenCurvePointDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			curvePointService.deleteCurvePoint(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The curve point id : 1, you want to delete, does not exist!");
		}

	}

}
