package com.microservices.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.microservices.accounts.dto.LoansDto;
@Component
public class LoansFallback implements LoansFeignClient{

	@Override
	public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId, String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
