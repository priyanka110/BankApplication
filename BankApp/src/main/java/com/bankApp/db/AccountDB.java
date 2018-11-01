package com.bankApp.db;

import java.util.List;
import java.util.Optional;

import com.bankApp.model.Account;
import com.bankApp.model.AccountType;
import com.bankApp.model.Customer;

public interface AccountDB {
	Account save (Account account);
	Optional<Account> findById(long id);
	void delete(Account account);
	List<Account> getCustomerAccountByType(Customer customer, AccountType type);
	
}
