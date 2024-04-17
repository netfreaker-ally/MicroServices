package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
@Schema(name="Accounts",description = "Schema to hold account information")

public class AccountsDto {
	@Schema(description = "AccountNumber of the EAzy Bank account")

	@Pattern(regexp="(^$|[0-9]{10})",message = "Account number must be 10 digits")
	@NotEmpty(message = "Account number cannot be empty or null")
    private Long accountNumber;

		@NotEmpty(message = "Account Type cannot be empty or null")

		@Schema(description = "Account Type of the EAzy Bank account",example = "SAvings")
    private String accountType;

		@NotEmpty(message = "Branch cannot be empty or null")
		@Schema(description = "Branch of the EAzy Bank account")
    private String branchAddress;


	public Long getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public String getBranchAddress() {
		return branchAddress;
	}


	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
    
}
