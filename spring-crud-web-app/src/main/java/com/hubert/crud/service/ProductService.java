package com.hubert.crud.service;

import java.util.List;

import com.hubert.crud.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	void saveProduct(Product product);
}
