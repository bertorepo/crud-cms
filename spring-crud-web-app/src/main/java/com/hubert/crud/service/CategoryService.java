package com.hubert.crud.service;

import java.util.List;

import com.hubert.crud.model.Category;

public interface CategoryService {
	
	List<Category> getProductCategory();
	void saveCategory(Category category);
	Category getCategory(long id);
	void deleteCategory(long id);
}
