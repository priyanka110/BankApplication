package com.bankApp.db;

import java.util.Optional;

import com.bankApp.model.Customer;

public interface CustomerDB {
	Customer save (Customer customer);
	Optional<Customer> findById(long id);
	void delete(Customer customer);

}
