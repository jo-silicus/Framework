package com.service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserMasterDao;
import com.model.UserMaster;
@Service
@Transactional
public class UserMasterServiceImpl implements UserMasterService{
	final static Logger logger = Logger.getLogger(UserMasterServiceImpl.class);
	
	@Autowired
	private UserMasterDao userDao;

	@Override
	public UserMaster authenticateUser(UserMaster user) {
		logger.info("inside UserMasterServiceImpl :: authenticateUser Method");
		return userDao.authenticateUser(user);
	}

	@Override
	public UserMaster saveUser(UserMaster user) {
		logger.info("inside save service method:::::");
		return userDao.saveUser(user);
	}

	@Override
	public UserMaster validateUser(String email, String password) {
		
		return userDao.validateUser(password, email);
	}

	@Override
	public boolean isUserExist(UserMaster user) {
		logger.info("inside  isUserExist method::");
		if(userDao.validateUser(user.getPassword(), user.getEmail())==null)
		return true;
		return false;
	}

	

	
}
