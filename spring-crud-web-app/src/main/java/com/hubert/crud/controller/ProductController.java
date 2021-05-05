package com.hubert.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hubert.crud.model.Product;
import com.hubert.crud.service.CategoryService;
import com.hubert.crud.service.ProductServiceImpl;

@Controller
public class ProductController {

	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/product")
	public String showProductPage(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("productList", products);

		return "product-home";
	}

	@GetMapping("/new-product")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categoryList", categoryService.getProductCategory());
		return "new-product";
	}

	@RequestMapping(value = "/save-product", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
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
}
