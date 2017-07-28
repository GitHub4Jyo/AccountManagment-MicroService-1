package com.microservice.account.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.microservice.account.bo.CustomerBO;

@Repository("mongoDAO")
public class MongoImpl implements MongoDAO{
	
	@Autowired
	private MongoOperations mongoOperations;

	/**
	 * Below method is to save user data to Mongo DB.
	 */
	@Override
	public Boolean registerUser(CustomerBO urb) {
		//save method is used to save the data to Mongo.
		mongoOperations.save(urb);
		return true;
	}

	@Override
	public List<CustomerBO> findUsers(String key, String value) {
		Query query = new Query();
		query.addCriteria(Criteria.where(key).is(value));
		List<CustomerBO> customer = mongoOperations.find(query, CustomerBO.class);
		return customer;
	}

	@Override
	public Object findUserByEmailId(String emailId) {
		return mongoOperations.find(new Query().addCriteria(Criteria.where("email").is(emailId)), CustomerBO.class);
	}

}
