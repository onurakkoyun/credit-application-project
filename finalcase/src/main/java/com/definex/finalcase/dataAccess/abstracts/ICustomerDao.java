package com.definex.finalcase.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import com.definex.finalcase.entities.concretes.Customer;

public interface ICustomerDao extends JpaRepository<Customer, Integer>{
	
	boolean existsCustomerByIdentityNumber(String identityNumber);
	
	Customer getCustomerById(Customer customer);
	Customer getByIdentityNumber(String identityNumber);
	

}
