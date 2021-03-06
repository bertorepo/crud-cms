package com.hubert.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.hubert.crud.model.Customer;


public interface CustomerImpl {
	List<Customer> getCustomers();
	void saveCustomer(Customer customer);
	Customer getCustomerId(long id);
	void deleteCustomer(long id);
	Optional<Customer> findCustomerEmail(Customer customer);
	//method for pagination
	
	Page<Customer> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
