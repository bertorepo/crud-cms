package com.hubert.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hubert.crud.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	void saveProduct(Product product);
	void deleteProduct(long id);
	Product getProduct(long id);
	
	Page<Product> findPaginatedProduct(int pageNumber, int pageSize, String sortField, String sortDirection);
	
}
