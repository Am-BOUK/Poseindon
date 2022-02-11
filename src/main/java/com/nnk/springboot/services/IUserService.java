package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;

/**
 * 
 * business interface that will allow implementation of application-specific
 * business processing
 * 
 * The important part is, the return value from operations which is a User
 * object
 * 
 * It is used to interact with the database, defining method related to the
 * User entity. Then is called/autowired in a controller layer.
 */
public interface IUserService {

	public User getUser(Integer id) throws EntityNotFoundException;

	public User addNewUser(User user) throws EntityAlreadyExistException;

	public User updateUser(Integer id, User user) throws EntityNotFoundException;

	public List<User> usersList();

	public boolean deleteUser(Integer id) throws EntityNotFoundException;

}
