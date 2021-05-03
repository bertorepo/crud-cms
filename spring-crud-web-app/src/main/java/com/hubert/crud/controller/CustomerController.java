package com.hubert.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hubert.crud.model.Customer;
import com.hubert.crud.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listCustomers", customerService.getCustomers());
		return "index";
	}

	@GetMapping("/new-customer")
	public String newCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "new-customer";
	}

//	@RequestMapping(value = "/save-customer", method = RequestMethod.POST)
	@PostMapping("/save-customer")
	public String saveStudent(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/";
	}
	
	@RequestMapping("/editCustomer/{id}")
	public ModelAndView editCustomer(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("new-customer");
		Customer customer = customerService.getCustomerId(id);
		mav.addObject(customer);
		return mav;
	}
	
	@RequestMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable(name = "id") int id) {
		customerService.deleteCustomer(id);
		return "redirect:/";
	}
}
