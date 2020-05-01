package com.gautam.dao;

public interface AdminDAO {

//	public String addAdmin(String id, String password) throws Exception;
	
	public Boolean validateAdmin(String id, String password) throws Exception;
	
}
