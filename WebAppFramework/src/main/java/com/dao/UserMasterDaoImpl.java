package com.dao;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.UserMaster;



@Repository
public class UserMasterDaoImpl implements UserMasterDao {
	final static Logger logger = Logger.getLogger(UserMasterDaoImpl.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public UserMaster authenticateUser(UserMaster user) {
		logger.info("inside UserMasterDaoImpl :: authenticateUser Method");
		logger.info(user.getUserName());
		return (UserMaster) sessionFactory.getCurrentSession().createQuery("from UserMaster user where user.password=:password and user.userName=:username")
				.setParameter("password", user.getPassword()).setParameter("username", user.getUserName()).uniqueResult();
	}

	public UserMaster validateUser(String password, String email) {
		logger.info(password+" "+email);
		
		 return (UserMaster) sessionFactory.getCurrentSession().createQuery("select user from UserMaster user where user.password=:password and user.email=:email")
				 .setParameter("password", password).setParameter("email", email).uniqueResult();
	}

	@Override
	public UserMaster saveUser(UserMaster user) {
		 sessionFactory.getCurrentSession().save(user);
		 return user;
	}

	



}
