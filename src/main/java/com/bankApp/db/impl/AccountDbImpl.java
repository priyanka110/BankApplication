package com.bankApp.db.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bankApp.db.AccountDB;
import com.bankApp.db.CustomerDB;
import com.bankApp.model.Account;
import com.bankApp.model.AccountType;
import com.bankApp.model.Customer;
import com.bankApp.util.BankUtil.AccountStatus;
import com.bankApp.util.BankUtil.CustomerStatus;

@Service
@SuppressWarnings("deprecation")
public class AccountDbImpl implements AccountDB {
   
     @Repository
	public interface AccountRepository extends CrudRepository<Account, Long> {

    	 List<Account> findByCustomerAndTypeIn(Customer customer, AccountType type);

		List<Account> findByCustomerIn(Customer customer);
    	
	}
    
    @Autowired
    private AccountRepository accountRepository;

   
  
	@Override
    public void delete(Account account) {
        Assert.notNull(account);
        account.setStatus(AccountStatus.CLOSED);
        accountRepository.save(account);
       
    }

    

	
	

	@Override
	public Account save(Account account) {
		
		 Assert.notNull(account);
	        account.setStatus(AccountStatus.OPEN);
		return accountRepository.save(account);
	}

	@Override
	public Optional<Account> findById(long id) {
		  Assert.notNull(id);
	        return accountRepository.findById(id);
	}

	
	public List<Account> getCustomerAccountByType(Customer customer, AccountType type)
	{
		Assert.notNull(customer, "Customer should not be null");
		Assert.notNull(type, "Type should not be null");
		return accountRepository.findByCustomerAndTypeIn(customer, type);
	}
	

	public List<Account> getCustomerAccounts(Customer customer)
	{
		
		Assert.notNull(customer, "Customer should not be null");
		return accountRepository.findByCustomerIn(customer);
	}
}
