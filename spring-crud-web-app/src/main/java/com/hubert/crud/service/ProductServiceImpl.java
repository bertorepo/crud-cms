package com.hubert.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubert.crud.model.Product;

import com.hubert.crud.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product getProduct(long id) {
		return productRepository.findById(id).get();
	}

}
