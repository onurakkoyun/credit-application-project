package com.definex.finalcase.business.abstracts;

import java.util.List;

import com.definex.finalcase.core.utilities.results.DataResult;
import com.definex.finalcase.core.utilities.results.Result;
import com.definex.finalcase.entities.concretes.Customer;


public interface ICustomerService {
	
	DataResult<List<Customer>> getAll();
	
	Customer getCustomerById(Customer newCustomer, int id);
	
	Result add(Customer customer);
	Result delete(int id);
	
	public boolean existsCustomerByIdentityNumber(String identityNumber);
	

}
