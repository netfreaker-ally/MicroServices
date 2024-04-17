package com.microservices.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Schema(name="Customer",description = "Schema to hold customer and account information")
public class CustomerDto {
	@Schema(description = "Name of the customer",example = "EAxy Bytes")
	@NotEmpty(message = "Name cant be empty or null")
	@Size(min = 5,max = 30,message = "the length of the name must be between 5 and 30")
	private String name;
	@Schema(description = "Email of the customer",example = "EAxyBytes@gmail.com")

	@NotEmpty(message = "email Address cant be empty or null")
	@Email(message = "Email address should be valid value")
	private String email;
	@Schema(description = "MobileNumber of the customer",example = "1234567890")

	@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	private String mobileNumber;
	@Schema(description = "AccountDetails of the customer",example = "1234567890")

	private AccountsDto accountsDto;

	public CustomerDto(String name, String email, String mobileNumber, AccountsDto accountsDto) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.accountsDto = accountsDto;
	}



	public CustomerDto() {
	super();
	// TODO Auto-generated constructor stub
}

	public AccountsDto getAccountsDto() {
		return accountsDto;
	}

	public void setAccountsDto(AccountsDto accountsDto) {
		this.accountsDto = accountsDto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}
