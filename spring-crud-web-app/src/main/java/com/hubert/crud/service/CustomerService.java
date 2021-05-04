package com.hubert.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hubert.crud.model.Customer;
import com.hubert.crud.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerImpl {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerId(long id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public void deleteCustomer(long id) {
		customerRepository.deleteById(id);
	}

	//Pagination
	@Override
	public Page<Customer> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		
		//sort
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		
		//pagination
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		return customerRepository.findAll(pageable);
	}


}
