package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.UserRepository;

/**
 * implementation of User business processing
 * 
 */
@Service
public class UserServiceImpl implements IUserService {
	private static final Logger logger = LogManager.getLogger("UserServiceImpl");

	/**
	 * Calling operations of the UserRepository
	 */
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Get One user object ** This operation allows to check if the id of the user
	 * we want to get its information already exist in the database, then allows to
	 * use its id to get him
	 * 
	 * @param id : id of the user object whose we want to get
	 * @return user object if it exists
	 * @throws EntityNotFoundException
	 */
	@Override
	public User getUser(Integer id) throws EntityNotFoundException {
		logger.info("Getting user : " + id);
		Optional<User> userFound = userRepository.findById(id);
		if (userFound.isEmpty()) {
			logger.error("The user : " + id + ", you want to get, does not exist!");
			throw new EntityNotFoundException("The user : " + id + ", you want to get, does not exist!");
		}
		logger.info("User : " + id + ", found");
		return userFound.get();
	}

	/**
	 * 
	 * Add a new user ** This operation allows to check if the id of the user we
	 * want to add already exists in the database, then allows to add it
	 * 
	 *
	 * @param user : user object to add
	 * @return user object added
	 * @throws EntityAlreadyExistException
	 */
	@Override
	public User addNewUser(User user) throws EntityAlreadyExistException {
		logger.info("adding new Bid");
		Optional<User> userFound = userRepository.findByUsername(user.getUsername());
		if (userFound.isPresent()) {
			logger.error("The username : " + user.getUsername() + ", you want to add, is already exists!");
			throw new EntityAlreadyExistException("The username : " + user.getUsername() + ", is already exists!");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);

	}

	/**
	 * Update information of the user ** This operation allows to check if the id of
	 * the user we want to update already exists in the database,
	 * 
	 * @param user : the user Object updated
	 * @return user object updated
	 * @throws Exception
	 */
	@Override
	public User updateUser(Integer id, User user) throws EntityNotFoundException {
		logger.info("updating new Bid");

		Optional<User> userFound = userRepository.findById(id);
		if (userFound.isEmpty()) {
			logger.error("The user id : " + id + ", you want to update, does not exist!");
			throw new EntityNotFoundException("The user id : " + id + ", you want to update, does not exist!");
		}
		userFound.get().setFullname(user.getFullname() != null ? user.getFullname() : userFound.get().getFullname());
		userFound.get().setPassword(user.getPassword() != null ? passwordEncoder.encode(user.getPassword())
				: userFound.get().getPassword());
		userFound.get().setPassword(passwordEncoder.encode(user.getPassword()));
		userFound.get().setRole(user.getRole() != null ? user.getRole() : userFound.get().getRole());
		userFound.get().setUsername(userFound.get().getUsername());

		return userRepository.saveAndFlush(userFound.get());

	}

	/**
	 * get all users
	 * 
	 * @return list of all users existing in the database
	 */
	@Override
	public List<User> usersList() {
		logger.info("get list of users");
		return userRepository.findAll();

	}

	/**
	 * Delete the user ** This operation allows to check if the id of te user we
	 * want to delete already exist in the database, then allows to use its id to
	 * delete it
	 * 
	 * @param id : id of the user we want to delete
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteUser(Integer id) throws EntityNotFoundException {
		logger.info("deleting user");
		Optional<User> userFound = userRepository.findById(id);
		if (userFound.isEmpty()) {
			logger.error("The user id : " + id + ", you want to delete, does not exist!");
			throw new EntityNotFoundException("The user id : " + id + ", you want to delete, does not exist!");
		}
		logger.info("delete user id : " + id);
		userRepository.delete(userFound.get());
		return true;
	}
}
