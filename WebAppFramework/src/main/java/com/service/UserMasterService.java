package com.service;

import com.model.UserMaster;

public interface UserMasterService
{
	UserMaster authenticateUser(UserMaster user);
	UserMaster saveUser(UserMaster user);
	UserMaster validateUser(String email,String password);
	boolean isUserExist(UserMaster user);
}
