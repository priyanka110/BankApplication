package com.bankApp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bankApp.util.BankUtil.CustomerStatus;



@Entity
@Table(name="Customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cId;
	
	private String name;
	
	private String password;

	 @OneToMany(mappedBy="customer")
	private List<Account> accounts;
	
	private CustomerStatus status;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getCId() {
		return cId;
	}

	public void setCId(Long cID) {
		this.cId = cID;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

@Override
public String toString()
{
	return this.getCId()+ " :: " + this.getName();
}
}
