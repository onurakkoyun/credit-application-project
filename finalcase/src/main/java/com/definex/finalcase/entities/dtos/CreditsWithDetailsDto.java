package com.definex.finalcase.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditsWithDetailsDto {
	private int creditId;
	
	private String creditResult;
	
	private double creditLimit;
	
    private String identityNumber;
	
	private String firstName;
	
	private String lastName;
	
	private int assurance;

}
