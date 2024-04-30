package com.microservices.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.microservices.accounts.Controller.AccountController;
import com.microservices.accounts.Entities.Accounts;
import com.microservices.accounts.Entities.Customer;
import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.AccountsDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.dto.AccountsMsgDto;

import com.microservices.accounts.exception.CustomerAlreadyExistsException;
import com.microservices.accounts.exception.ResourceNotFoundException;
import com.microservices.accounts.mapper.AccountsMapper;
import com.microservices.accounts.mapper.CustomerMapper;
import com.microservices.accounts.repository.AccountsRepository;
import com.microservices.accounts.repository.CustomerRepository;
import com.microservices.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{
	@Autowired
	private AccountsRepository  accountsRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private final StreamBridge streamBridge;
	
	public AccountsServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository,
			StreamBridge streamBridge) {
		super();
		this.accountsRepository = accountsRepository;
		this.customerRepository = customerRepository;
		this.streamBridge = streamBridge;
	}

	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	 @Override
	    public void createAccount(CustomerDto customerDto) {
	        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
	        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
	        if(optionalCustomer.isPresent()) {
	            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
	                    +customerDto.getMobileNumber());
	        }
	        Customer savedCustomer = customerRepository.save(customer);
	        Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));
	        sendCommunication(savedAccount, savedCustomer);
	    }

	    private void sendCommunication(Accounts account, Customer customer) {
	        var accountsMsgDto = new AccountsMsgDto(account.getAccountNumber(), customer.getName(),
	                customer.getEmail(), customer.getMobileNumber());
	        log.info("Sending Communication request for the details: {}", accountsMsgDto);
	        var result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
	        log.info("Is the Communication request successfully triggered ? : {}", result);
	    }
	  private Accounts createNewAccount(Customer customer) {
	        Accounts newAccount = new Accounts();
	        newAccount.setCustomerId(customer.getCustomerId());
	        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

	        newAccount.setAccountNumber(randomAccNumber);
	        newAccount.setAccountType(AccountsConstants.SAVINGS);
	        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
	      
			
	        return newAccount;
	    }
	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		// TODO Auto-generated method stub
		Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));
		Accounts account= accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
		CustomerDto customerDto= CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
		return customerDto;
	}
	 @Override
	    public boolean updateAccount(CustomerDto customerDto) {
	        boolean isUpdated = false;
	        AccountsDto accountsDto = customerDto.getAccountsDto();
	        if(accountsDto !=null ){
	            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
	                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
	            );
	            AccountsMapper.mapToAccounts(accountsDto, accounts);
	            accounts = accountsRepository.save(accounts);

	            Long customerId = accounts.getCustomerId();
	            Customer customer = customerRepository.findById(customerId).orElseThrow(
	                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
	            );
	            CustomerMapper.mapToCustomer(customerDto,customer);
	            customerRepository.save(customer);
	            isUpdated = true;
	        }
	        return  isUpdated;
	    }

	 @Override
	    public boolean deleteAccount(String mobileNumber) {
	        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
	                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
	        );
	        accountsRepository.deleteByCustomerId(customer.getCustomerId());
	        customerRepository.deleteById(customer.getCustomerId());
	        return true;
	    }

	 @Override
	    public boolean updateCommunicationStatus(Long accountNumber) {
	        boolean isUpdated = false;
	        if(accountNumber !=null ){
	            Accounts accounts = accountsRepository.findById(accountNumber).orElseThrow(
	                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber.toString())
	            );
	            accounts.setCommunicationSw(true);
	            accountsRepository.save(accounts);
	            isUpdated = true;
	        }
	        return  isUpdated;
	    }

}
