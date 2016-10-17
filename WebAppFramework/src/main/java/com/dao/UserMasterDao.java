package com.dao;

import java.io.Serializable;

import com.model.UserMaster;

public interface UserMasterDao extends Serializable
{
	UserMaster authenticateUser(UserMaster user);
	UserMaster validateUser(String password,String email);
	UserMaster saveUser(UserMaster user);
}
