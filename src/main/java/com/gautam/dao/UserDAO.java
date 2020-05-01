package com.gautam.dao;

import com.gautam.model.User;

public interface UserDAO {
	
	public String addUser(User user) throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public String updateUserName(String userId, String firstName, String lastName) throws Exception;
	
	public String updatePassword(String userId, String password) throws Exception;
	
}
