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
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class RuleNameServiceTest {

	@Mock
	private RuleNameRepository ruleNameRepository;

	@InjectMocks
	private RuleNameServiceImpl ruleNameService;

	@Test
	public void getRuleName() throws EntityNotFoundException {
		RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		ruleName.setId(1);
		Optional<RuleName> optionalRuleName = Optional.of(ruleName);
		when(ruleNameRepository.findById(1)).thenReturn(optionalRuleName);
		RuleName result = ruleNameService.getRuleName(1);

		assertEquals(result.getId(), 1);
	}

	@Test
	public void getRuleName_whenRuleNameDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			ruleNameService.getRuleName(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The RuleName : 1, you want to get, does not exist!");
		}

	}

	@Test
	public void addNewRuleNameTest() {
		RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

		RuleName result = ruleNameService.addNewRuleName(ruleName);
		assertEquals(result.getDescription(), "Description");

	}

	@Test
	public void updateRuleNameTest() throws EntityNotFoundException {
		RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		ruleName.setId(1);
		Optional<RuleName> optionalRuleName = Optional.of(ruleName);
		RuleName ruleNameUpdated = new RuleName();
		ruleNameUpdated.setDescription("test");
		when(ruleNameRepository.findById(1)).thenReturn(optionalRuleName);
		when(ruleNameRepository.saveAndFlush(ruleName)).thenReturn(ruleName);

		RuleName result = ruleNameService.updateRuleName(1, ruleNameUpdated);
		assertEquals(result.getDescription(), "test");

	}

	@Test
	public void updateRuleNameTest_whenRuleNameDoesNotExist_shouldReturnEntityNotFoundException() {
		RuleName ruleNameUpdated = new RuleName();
		ruleNameUpdated.setDescription("test");
		try {
			ruleNameService.updateRuleName(2, ruleNameUpdated);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The ruleName id : 2, you want to update, does not exist!");
		}

	}

	@Test
	public void ruleNameListTest() {
		RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		List<RuleName> ruleNameList = new ArrayList<>();
		ruleNameList.add(ruleName);
		when(ruleNameRepository.findAll()).thenReturn(ruleNameList);

		List<RuleName> result = ruleNameService.ruleNameList();
		assertThat(result.contains(ruleName));
	}

	@Test
	public void deleteRuleNameTest() throws EntityNotFoundException {
		RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		ruleName.setId(1);
		Optional<RuleName> optionalRuleName = Optional.of(ruleName);
		when(ruleNameRepository.findById(1)).thenReturn(optionalRuleName);

		boolean resultat = ruleNameService.deleteRuleName(1);

		assertTrue(resultat);

	}

	@Test
	public void deleteRuleNameTest_whenRuleNameDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			ruleNameService.deleteRuleName(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The ruleName id : 1, you want to delete, does not exist!");
		}

	}

}
