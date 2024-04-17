package com.microservices.accounts.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.AccountsContactInfoDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.dto.ErrorResponseDto;
import com.microservices.accounts.dto.ResponseDto;
import com.microservices.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
@Tag(
		name="CRUD REST Apis for Accounts in EazyBank",
		description = "CRUD RestAPI 's for create,delete,update,fetch"
		)

@RestController
@RequestMapping(path="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {
	//when you have single constructor you dont need to use autowire
	@Autowired
	private final IAccountsService iacccountService;
	@Value("${build.version}")
	private String buildVersion;
	@Autowired
	private Environment environment;
	@Autowired
	private AccountsContactInfoDto accountsContactInfoDto;
	
	public AccountController(IAccountsService iacccountService) {
		super();
		this.iacccountService = iacccountService;
	}
	@Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
	
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		iacccountService.createAccount(customerDto);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
	}
	 @Operation(
	            summary = "Fetch Account Details REST API",
	            description = "REST API to fetch Customer &  Account details based on a mobile number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAcountDetails(@RequestParam 	@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")

			String mobileNumber){
	CustomerDto customerDto=	iacccountService.fetchAccount(mobileNumber);
	return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}
	   @Operation(
	            summary = "Update Account Details REST API",
	            description = "REST API to update Customer &  Account details based on a account number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "417",
	                    description = "Expectation Failed"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	@PutMapping("/update")
	    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
	        boolean isUpdated = iacccountService.updateAccount(customerDto);
	        System.out.println("SAdg");
	        if(isUpdated) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
	        }
	    }
	   @Operation(
	            summary = "Delete Account & Customer Details REST API",
	            description = "REST API to delete Customer &  Account details based on a mobile number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "417",
	                    description = "Expectation Failed"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	@DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam 	@Pattern(regexp="(^$|[0-9]{10})",message = "Account number must be 10 digits")

    		String mobileNumber) {
        boolean isDeleted = iacccountService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
	   @Operation(
	            summary = "Get Build Information",
	            description = "Get Build Information in   Account details MicrServices"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	   @GetMapping("/build-info")
	   public ResponseEntity<String> getBuildInfo(){
		   return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	   }
	   @Operation(
	            summary = "Get Java Information",
	            description = "Get Java Information in   Account details MicrServices"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	   @GetMapping("/java-version")
	   public ResponseEntity<String> getJavaVersion(){
		   
		   return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("TEMP"));
	   }
	   @Operation(
	            summary = "Get Contact Info",
	            description = "Contact Info details that can be reached out in case of any issues"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	    @GetMapping("/contact-info")
	    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(accountsContactInfoDto);
	    }
}

