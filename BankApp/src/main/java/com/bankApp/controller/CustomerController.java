package com.bankApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.db.AccountDB;
import com.bankApp.db.CustomerDB;
import com.bankApp.db.impl.CustomerDbImpl;
import com.bankApp.model.Account;
import com.bankApp.model.Customer;
import com.bankApp.util.BankUtil.AccountStatus;
import com.bankApp.util.BankUtil.CustomerStatus;
import com.bankApp.util.BankUtil.TransactionType;

@RestController
public class CustomerController extends BaseController{

	  static final Logger logger = Logger.getLogger(CustomerController.class.getName());
	  
	  @Autowired
	  CustomerDB customerDb;
	  
	  @Autowired
	  AccountDB accountDb;
	  
		@RequestMapping(value = "customer/create", method = RequestMethod.POST)
		public Map<String, Object> saveCustomer ( 
				  @RequestBody Customer customer, HttpServletRequest request) {
			
			customerDb.save(customer);
			
	return	createResponse(true, customer.toString());
		}
		
		@RequestMapping(value = "customer/deactivate", method = RequestMethod.GET)
		public Map<String, Object> deactivateCustomer ( 
				@RequestParam (value = "custId", required = true) Long custId, HttpServletRequest request) {
			Optional<Customer> customer = customerDb.findById(custId);
			if(customer.isPresent())
			{
				  for(Account acc: customer.get().getAccounts())
			        {
			        accountDb.delete(acc);	
			        }
			customerDb.delete(customer.get());
			
	return	createResponse(true, "Customer Deactivated");
			}
			else return createResponse(false, "Customer Id invalid");
		}
		
		@RequestMapping(value = "customer/createAccount", method = RequestMethod.POST)
		public Map<String, Object> createAccount ( 
				  @RequestBody Account account, HttpServletRequest request) {
			
			List<Account> accounts = accountDb.getCustomerAccountByType(customerDb.findById(account.getCustId()).get(), account.getType());
			if(accounts.size()>0)
			{
				return createResponse(false, "Customer already has an accoutn of type : "+ account.getType().name());
			
			}
			else
			{
				account.setCustomer(customerDb.findById(account.getCustId()).get());
				accountDb.save(account);
				return createResponse(true, ""+account.getType().name()+" Account created for Customer " + account.getCustomer().toString());
			}
		}
		

		@RequestMapping(value = "customer/viewDetails", method = RequestMethod.GET)
		public Map<String, Object> amountTransact ( 
				@RequestParam (value = "custId", required = true) Long  id,
				 HttpServletRequest request) {
		
		    Optional<Customer> customer = customerDb.findById(id);
			Customer custCopy= customer.get();
		    if(!customer.isPresent()) {
			return createResponse(false, "Customer id invalid");
		    }
		   
		    else return createResponse(true, custCopy);
}

	
}
