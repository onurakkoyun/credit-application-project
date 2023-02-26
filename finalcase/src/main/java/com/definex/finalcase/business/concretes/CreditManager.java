package com.definex.finalcase.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.definex.finalcase.business.abstracts.ICreditService;
import com.definex.finalcase.core.utilities.results.DataResult;
import com.definex.finalcase.core.utilities.results.ErrorDataResult;
import com.definex.finalcase.core.utilities.results.ErrorResult;
import com.definex.finalcase.core.utilities.results.Result;
import com.definex.finalcase.core.utilities.results.SuccessDataResult;
import com.definex.finalcase.core.utilities.results.SuccessResult;
import com.definex.finalcase.dataAccess.abstracts.ICreditDao;
import com.definex.finalcase.dataAccess.abstracts.ICustomerDao;
import com.definex.finalcase.entities.concretes.Credit;
import com.definex.finalcase.entities.concretes.Customer;
import com.definex.finalcase.entities.dtos.CreditsWithDetailsDto;



@Service
public class CreditManager implements ICreditService{
	
	
	private ICreditDao creditDao;
	private ICustomerDao customerDao;

	@Autowired
	public CreditManager(ICreditDao creditDao, ICustomerDao customerDao) {
		super();
		this.creditDao = creditDao;
		this.customerDao = customerDao;
	}
	
	@Override
	public DataResult<List<CreditsWithDetailsDto>> getCreditsWithDetailsDto(String identityNumer) {
		if ( !creditDao.getCreditsWithDetailsDto(identityNumer).isEmpty() ) {
			return new SuccessDataResult<List<CreditsWithDetailsDto>>
			(this.creditDao.getCreditsWithDetailsDto(identityNumer), "Kredi Sonucu");
		}
		else {
			return new ErrorDataResult<>(identityNumer + " kimlik numaralı kişinin kredi başvurusu bulunmamaktadır.");
		}
		
	}



	@Override
	public Result applyCreditByIdentityNumber(@RequestParam String identityNumer) {
		
		Customer customer = customerDao.getByIdentityNumber(identityNumer);
		Credit newCredit = new Credit();
		
		if (customer != null) {
			
		
		if (customer.getCreditScore() < 500) {
			newCredit.setCreditResult("Red");
			newCredit.setCustomer(customer);
			creditDao.save(newCredit);
			return new SuccessResult("Kredi başvurunuz yapıldı!, kimlik numarınız ile başvuru kontrol ekranından sonucuzu kontrol edebilirsiniz");
		}
		else if (customer.getCreditScore() >= 500 && customer.getCreditScore() < 1000 && customer.getMonthlyIncome() < 5000 ) {
			
			newCredit.setCreditResult("Onay");
			newCredit.setCreditLimit(10000 + (customer.getAssurance() * 0.1));
			newCredit.setCustomer(customer);
			creditDao.save(newCredit);		
			return new SuccessResult("Kredi başvurunuz yapıldı, kimlik numarınız ile kontrol ekranından başvuru sonucuzu kontrol edebilirsiniz.");
		}
		else if (customer.getCreditScore() >= 500 && customer.getCreditScore() < 1000 && 
				customer.getMonthlyIncome() >= 5000 && customer.getMonthlyIncome() <= 10000 ) {
			
			newCredit.setCreditResult("Onay");
			newCredit.setCreditLimit(20000 + (customer.getAssurance() * 0.2));
			newCredit.setCustomer(customer);
			creditDao.save(newCredit);		
			return new SuccessResult("Kredi başvurunuz yapıldı, kimlik numarınız ile kontrol ekranından başvuru sonucuzu kontrol edebilirsiniz.");
		}
		else if (customer.getCreditScore() >= 500 && customer.getCreditScore() < 1000 && 
				customer.getMonthlyIncome() > 10000) {
			
			newCredit.setCreditResult("Onay");
			newCredit.setCreditLimit((customer.getMonthlyIncome() * 2) + (customer.getAssurance() * 0.25));
			newCredit.setCustomer(customer);
			creditDao.save(newCredit);		
			return new SuccessResult("Kredi başvurunuz yapıldı, kimlik numarınız ile kontrol ekranından başvuru sonucuzu kontrol edebilirsiniz.");
		}
		
		else if (customer.getCreditScore() >= 1000 ) {
			
			newCredit.setCreditResult("Onay");
			newCredit.setCreditLimit((customer.getMonthlyIncome() * 4) + (customer.getAssurance() * 0.5));
			newCredit.setCustomer(customer);
			creditDao.save(newCredit);		
			return new SuccessResult("Kredi başvurunuz yapıldı, kimlik numarınız ile kontrol ekranından başvuru sonucuzu kontrol edebilirsiniz." );
		}
		
		}
		else {
			return new ErrorResult("Müşteri sisteme kayıtlı değil!");
		}
		return new ErrorResult("");
	}



	




	
	
	

}
