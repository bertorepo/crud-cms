package com.hubert.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hubert.crud.model.Customer;
import com.hubert.crud.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, model, "firstName", "asc");
	}

	@GetMapping("/new-customer")
	public String newCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "new-customer";
	}

	@RequestMapping(value = "/save-customer", method = RequestMethod.POST)
//	@PostMapping("/save-customer")
	public String saveStudent(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttr) {

//		Optional<Customer> findCustomerEmail = customerService.findCustomerEmail(customer);

		if (bindingResult.hasErrors()) {
			return "new-customer";
		}

//		if (findCustomerEmail.isPresent()) {
//			model.addAttribute("existedEmail", findCustomerEmail);
//			return "new-customer";
//		}

		customerService.saveCustomer(customer);
		redirectAttr.addFlashAttribute("success", "Customer save successfully");
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
	public String deleteCustomer(@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
		customerService.deleteCustomer(id);
		redirectAttr.addFlashAttribute("deleted", "Customer deleted successfully");
		return "redirect:/";
	}

	// Pagination

	@GetMapping("/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber, Model model,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDirection

	) {
		int pageSize = 10;

		Page<Customer> page = customerService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<Customer> customerList = page.getContent();

		// passing data to thymeleaf template in pagination
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		// passing data to thymeleaf template in sort
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDirection);
		model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");

		// pass the customer list
		model.addAttribute("listCustomers", customerList);

		return "index";

	}

}
