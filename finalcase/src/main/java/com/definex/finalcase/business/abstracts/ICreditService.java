package com.definex.finalcase.business.abstracts;

import java.util.List;

import com.definex.finalcase.core.utilities.results.DataResult;
import com.definex.finalcase.core.utilities.results.Result;
import com.definex.finalcase.entities.dtos.CreditsWithDetailsDto;


public interface ICreditService {
	
	Result applyCreditByIdentityNumber(String identityNumer);
	DataResult<List<CreditsWithDetailsDto>> getCreditsWithDetailsDto(String identityNumer);

}
