package com.hubert.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hubert.crud.model.Product;
import com.hubert.crud.service.ProductServiceImpl;

@Controller
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	@GetMapping("/product")
	public String showProductPage(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("productList", products);
		return "product-home";
	}
	
	@GetMapping("/new-product")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "new-product";
	}
	
	@RequestMapping(value = "/save-product", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		return "redirect:/product";
	}
}
