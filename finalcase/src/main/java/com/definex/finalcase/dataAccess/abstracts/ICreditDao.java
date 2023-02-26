package com.definex.finalcase.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.definex.finalcase.entities.concretes.Credit;
import com.definex.finalcase.entities.dtos.CreditsWithDetailsDto;


public interface ICreditDao extends JpaRepository<Credit, Integer>{	
	
	@Query("Select new com.definex.finalcase.entities.dtos.CreditsWithDetailsDto(cr.creditId, cr.creditResult, cr.creditLimit, cs.identityNumber, cs.firstName, cs.lastName, cs.assurance) "
			+ "FROM Credit cr JOIN Customer cs ON cr.customer.id = cs.id  WHERE cs.identityNumber=:identityNumber")
	List<CreditsWithDetailsDto> getCreditsWithDetailsDto(@Param("identityNumber") String identityNumber);
}
