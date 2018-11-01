package com.bankApp.controller;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

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
import com.bankApp.model.Account;
import com.bankApp.model.Customer;
import com.bankApp.util.BankUtil.AccountStatus;
import com.bankApp.util.BankUtil.TransactionType;

@RestController
public class AccountController extends BaseController{
	
  static final Logger logger = Logger.getLogger(AccountController.class.getName());
	  
	  @Autowired
	  CustomerDB customerDb;
	  
	  @Autowired
	  AccountDB accountDb;

	  
	  
			@RequestMapping(value = "account/transact", method = RequestMethod.PUT)
			public Map<String, Object> amountTransact ( 
					@RequestParam (value = "accountId", required = true) Long  id,
					@RequestParam (value = "amount", required = true) Double amount,@RequestParam (value = "type", required = true) int type, HttpServletRequest request) {
			
			    Optional<Account> account = accountDb.findById(id);
				Account newAcc= account.get();
			    if(!account.isPresent()|| (account.get().getStatus().equals(AccountStatus.CLOSED))) {
				return createResponse(false, "Account id invalid");
			    }
			    else if (type==TransactionType.CREDIT.ordinal())
			    {
			    	
			    	newAcc.setBalance(account.get().getBalance()!=null?(account.get().getBalance()+amount):amount);
			    	accountDb.save(newAcc);
			    	return	createResponse(true, "New account balance for account " +newAcc.getId() + " is : " + newAcc.getBalance());
			    }
			    else if(type==TransactionType.DEBIT.ordinal() && newAcc.getBalance()!=null&&newAcc.getBalance()>amount) {
			    	newAcc.setBalance(account.get().getBalance()-amount);
			    	accountDb.save(newAcc);
			    	return	createResponse(true, "New account balance for account " +newAcc.getId() + " is : " + newAcc.getBalance());

			}
			    else return createResponse(false, "Balance insufficient");
}
}
