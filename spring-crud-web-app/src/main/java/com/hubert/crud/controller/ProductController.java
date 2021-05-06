package com.hubert.crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hubert.crud.model.Product;
import com.hubert.crud.service.CategoryService;
import com.hubert.crud.service.ProductServiceImpl;

@Controller
public class ProductController extends TrimmerEditor {

	@Override
	public void trimWhiteSpace(WebDataBinder binder) {
		super.trimWhiteSpace(binder);
	}

	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/product")
	public String showProductPage(Model model) {
		return findPaginatedProduct(1, "productName", "asc", model);
	}

	@GetMapping("/new-product")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categoryList", categoryService.getProductCategory());
		return "new-product";
	}

	@RequestMapping(value = "/save-product", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
		
		model.addAttribute("categoryList", categoryService.getProductCategory());
		if(bindingResult.hasErrors()){
			return "/new-product";
		}

		productService.saveProduct(product);
		return "redirect:/product";
	}

	@RequestMapping(value = "/editProduct/{id}")
	public ModelAndView editProduct(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("new-product");
		
		Product product = productService.getProduct(id);
		mav.addObject(product);
		mav.addObject("categoryList", categoryService.getProductCategory());
		return mav;
	}

	@RequestMapping(value = "/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		productService.deleteProduct(id);
		return "redirect:/product";
	}
	
	//Paginaton
	@GetMapping("/productPage/{pageNumber}")
		public String findPaginatedProduct(@PathVariable(value = "pageNumber")	int pageNumber,
				@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDirection,Model model) {
		
		int pageSize = 7;
		
		Page<Product> page = productService.findPaginatedProduct(pageNumber, pageSize, sortField, sortDirection);
		List<Product> productList = page.getContent();
		
		//passing data to view template
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDirection);
		model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
		
		//passing the paginated products to the view
		model.addAttribute("productList", productList);
		
		
		return "/product-home";
	}
	
	
}
