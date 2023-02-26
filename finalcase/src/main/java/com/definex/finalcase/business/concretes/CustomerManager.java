package com.definex.finalcase.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.definex.finalcase.business.abstracts.ICustomerService;
import com.definex.finalcase.core.utilities.results.DataResult;
import com.definex.finalcase.core.utilities.results.ErrorResult;
import com.definex.finalcase.core.utilities.results.Result;
import com.definex.finalcase.core.utilities.results.SuccessDataResult;
import com.definex.finalcase.core.utilities.results.SuccessResult;
import com.definex.finalcase.dataAccess.abstracts.ICustomerDao;
import com.definex.finalcase.entities.concretes.Customer;
import com.definex.finalcase.exception.UserNotFoundException;

@Service
public class CustomerManager implements ICustomerService{
	
	private ICustomerDao customerDao;
	
	@Autowired
	public CustomerManager(ICustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	public DataResult<List<Customer>> getAll() {
		return new SuccessDataResult<List<Customer>>
		(this.customerDao.findAll(), "Tüm müşteriler listelendi");
	}

	@Override
	public Result add(Customer customer) {
		if (existsCustomerByIdentityNumber(customer.getIdentityNumber())) {
			this.customerDao.save(customer);
			return new SuccessResult("Müşteri eklendi");
		}
		else {
			return new ErrorResult("Bu kimlik numarası sistemde kayıtlı!");
		}
		
	}
	
	@Override
	public Customer getCustomerById(Customer newCustomer, int id) {
		return this.customerDao.findById(id).map(customer ->{
			customer.setIdentityNumber(newCustomer.getIdentityNumber());
			customer.setFirstName(newCustomer.getFirstName());
			customer.setLastName(newCustomer.getLastName());
			customer.setMonthlyIncome(newCustomer.getMonthlyIncome());
			customer.setPhoneNumber(newCustomer.getPhoneNumber());
			return customerDao.save(customer);
		}).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	@Override
	public Result delete(int id) {
		this.customerDao.deleteById(id);
		return new SuccessResult("Müşteri silindi");
	}

	@Override
	public boolean existsCustomerByIdentityNumber(String identityNumber) {
		if (this.customerDao.existsCustomerByIdentityNumber(identityNumber)) {
			return false;
		}
		else {
			return true;
		}
	}

}
