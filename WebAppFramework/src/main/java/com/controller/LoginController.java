package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.UserMaster;
import com.service.UserMasterService;

@RestController
@RequestMapping("/user")
public class LoginController {

	final static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	UserMasterService userService;

	@RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> authenticateUser(@RequestBody UserMaster user,  HttpServletRequest request) {
		logger.info("inside LoginController :: authenticateUser Method");
		try {
			UserMaster userObj = userService.authenticateUser(user);
			if(userObj!=null){
				HttpSession session = request.getSession(true);
				session.setAttribute("userInfo", userObj);
			}else{
				throw new NullPointerException();
			}
			
		}catch(NullPointerException e){
			logger.error("NullPointerException in LoginController :: User Object null:: ", e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			logger.error("Exception in LoginController :: authenticateUser method:: Exception is ::", e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/*
	 * Retrieve All Users
	 */
	@RequestMapping(value = "/validateUser", method = RequestMethod.GET)
	public ResponseEntity<UserMaster> validateUser(@RequestParam String password, @RequestParam String email,
			HttpServletRequest httpServletRequest) {
		UserMaster user = userService.validateUser(email, password);

		if (user.isEmpty(user)) {
			return new ResponseEntity<UserMaster>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserMaster>(user, HttpStatus.OK);
	}

	/*
	 * Create a User
	 */
	@RequestMapping(value = "/adduser/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody UserMaster user) {
		/*
		 * User user= new User(); user.setEmail("pall@111.com");
		 * user.setUserName("pallavi"); user.setMobileNumber("9021568623");
		 * user.setPassword("9021568623");
		 */
		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getUserName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		System.out.println("++++inside=======createUser");

		HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

}
