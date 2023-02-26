package com.definex.finalcase.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.definex.finalcase.business.abstracts.ICustomerService;
import com.definex.finalcase.core.utilities.results.DataResult;
import com.definex.finalcase.core.utilities.results.ErrorDataResult;
import com.definex.finalcase.core.utilities.results.Result;
import com.definex.finalcase.entities.concretes.Customer;


@RestController
@RequestMapping("/api/customers")
public class CustomersController {
	
	private ICustomerService customerService;

	@Autowired
	public CustomersController(ICustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Customer>> getAll(){
		return this.customerService.getAll();
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@Valid @RequestBody Customer customer) {
		return ResponseEntity.ok(this.customerService.add(customer));
	}
	
	 @PutMapping("/getCustomerById/{id}")
	 public Customer getCustomerById(@RequestBody Customer newCustomer, @PathVariable int id) {
		 return this.customerService.getCustomerById(newCustomer, id);
	 }
	
	@DeleteMapping("/{id}")
	 public Result delete(@PathVariable("id") int id) {
	    return this.customerService.delete(id);
	 }
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String, String> validationErrors = new HashMap<String, String>();
		
		for (FieldError fieldError: exceptions.getBindingResult().getFieldErrors() ) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama Hataları");
		return errors;
	}
	

}
