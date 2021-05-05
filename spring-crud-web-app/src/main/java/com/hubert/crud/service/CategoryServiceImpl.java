package com.hubert.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubert.crud.model.Category;
import com.hubert.crud.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getProductCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category getCategory(long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}

}
