package com.nnk.springboot.services;

import java.security.Principal;

/**
 * 
 * business interface
 * 
 * The important part is, the return value from operations which is getUserInfo
 * 
 * It is used to interact with the database, defining method related to the
 * Principal user. Then is called/autowired in a controller layer.
 */
public interface IGetUserInfoService {
	String getUserInfo(Principal user);

}
