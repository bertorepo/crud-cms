package com.hubert.crud.service;

import java.util.List;

import com.hubert.crud.model.Customer;

public interface CustomerImpl {
	List<Customer> getCustomers();
	void saveCustomer(Customer customer);
	Customer getCustomerId(long id);
	void deleteCustomer(long id);
}
