package com.definex.finalcase.api.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.definex.finalcase.business.abstracts.ICreditService;
import com.definex.finalcase.core.utilities.results.DataResult;
import com.definex.finalcase.entities.dtos.CreditsWithDetailsDto;

@RestController
@RequestMapping("/api/credits")
public class CreditsController {
	
	private ICreditService creditService;
	
	
	@Autowired
	public CreditsController(ICreditService creditService) {
		super();
		this.creditService = creditService;
	}
	@PostMapping(value = "/applyCreditByIdentityNumber")
	public ResponseEntity<?> applyCreditByIdentityNumber(@RequestParam("identityNubmer") String identityNubmer) {
		return ResponseEntity.ok(this.creditService.applyCreditByIdentityNumber(identityNubmer));
	}
	
	@GetMapping("/getCreditResultWithIdentity")
	public DataResult<List<CreditsWithDetailsDto>> getCreditsWithDetailsDto(@RequestParam("identityNumber") String identityNumber){
		return this.creditService.getCreditsWithDetailsDto(identityNumber);
	}

}
