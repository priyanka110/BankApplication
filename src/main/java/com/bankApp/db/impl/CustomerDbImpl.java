package com.bankApp.db.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bankApp.db.CustomerDB;
import com.bankApp.model.Customer;
import com.bankApp.util.BankUtil.CustomerStatus;

@Service
@SuppressWarnings("deprecation")
public class CustomerDbImpl implements CustomerDB {
   
     @Repository
	public interface CustomerRepository extends CrudRepository<Customer, Long> {

		
    	
	}
    
    @Autowired
    private CustomerRepository customerRepository;

   
  
	@Override
    public void delete(Customer customer) {
        Assert.notNull(customer);
        customer.setStatus(CustomerStatus.INACTIVE);
        customerRepository.save(customer);
         
    }

    

	
	

	@Override
	public Customer save(Customer customer) {
		 Assert.notNull(customer);
	        customer.setStatus(CustomerStatus.ACTIVE);
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> findById(long id) {
		  Assert.notNull(id);
	        return customerRepository.findById(id);
	}

}
