package com.nnk.springboot.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void UserTest() {
		User user = new User("UserName", "Password@123", "Full Name", "Role");

		// Save
		user = userRepository.save(user);
		Assert.assertNotNull(user.getId());
		Assert.assertTrue(user.getUsername().equals("UserName"));

		// Update
		user.setUsername("UserNameUpdated");
		user = userRepository.save(user);
		Assert.assertTrue(user.getUsername().equals("UserNameUpdated"));

		// Find
		List<User> listResult = userRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = user.getId();
		userRepository.delete(user);
		Optional<User> userList = userRepository.findById(id);
		Assert.assertFalse(userList.isPresent());
	}
}
