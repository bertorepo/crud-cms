package com.hubert.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hubert.crud.model.Category;
import com.hubert.crud.service.CategoryServiceImpl;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@GetMapping("/category")
	public String showCategoryPage(Model model) {
		
		model.addAttribute("categoryList", categoryService.getProductCategory());
		
		return "category-home";
	}
	
	@GetMapping("/new-category")
	public String newCategory(Model model) {
		model.addAttribute("category", new Category());
		return "/new-category";
	}
	
	@RequestMapping(value = "/save-category", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("category") Category category) {
		categoryService.saveCategory(category);
		
		return "redirect:/category";
	}
	
	@RequestMapping(value = "/editCategory/{id}")
	public ModelAndView editCategory(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView("new-category");
		Category category = categoryService.getCategory(id);
		mav.addObject(category);
		return mav;
	}
	
	@RequestMapping(value = "/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(name ="id") int id) {
		categoryService.deleteCategory(id);
		return "redirect:/category";
	}
}
