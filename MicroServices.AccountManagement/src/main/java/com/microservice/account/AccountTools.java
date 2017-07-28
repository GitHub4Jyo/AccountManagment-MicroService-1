package com.microservice.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservice.account.bo.CustomerBO;
import com.microservice.account.dao.MongoDAO;

@Component
public class AccountTools {
	
	@Autowired
	public MongoDAO mongoDAO;

	public Boolean registerUser(CustomerBO user) {
		return mongoDAO.registerUser(user);
	}
	
	public Object findUsers(String key, String value) {
		return mongoDAO.findUsers(key, value);
	}

	
	@SuppressWarnings("unchecked")
	public Boolean isUserExitByEmail(String emailId) {
		List<CustomerBO> customer = (List<CustomerBO>) mongoDAO.findUserByEmailId(emailId);
		System.out.println(customer.toString());
		if(null == customer || customer.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
}
