package com.app.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.raghu.entity.Employee;
import com.app.raghu.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService service;

	@GetMapping("/register")
	public String showRegister() {
		return "EmployeeRegister";
	}

	// 2. Read Form data and insert in DB
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Employee employee, Model model) {
		// call service and get id
		Integer id = service.saveEmployee(employee);
		// construct message
		String message = "Employee '" + id + "' Created";
		// send message to UI
		model.addAttribute("message", message);
		// goto UI
		return "EmployeeRegister";
	}

	@GetMapping("/all")
	public String showAll(@RequestParam(required = false) String message, Model model) {
		// fetch data from DB using service
		List<Employee> list = service.getAllEmployees();

		// send this data to UI/View
		model.addAttribute("list", list);
		model.addAttribute("message", message);

		// goto UI Page
		return "EmployeeData";
	}

	@GetMapping("/edit")
		public String showEdit(
				@RequestParam Integer id,
				Model model
				)
		{
			
			  String page = null; try { //fetch data from DB using service Employee
			  employee = service.getOneEmployee(id); //send to UI
			  model.addAttribute("employee", employee); //goto edit page page =
			  "EmployeeEdit"; } catch (EmployeeNotFoundException e) { e.printStackTrace();
			  //read exception message model.addAttribute("message", e.getMessage());
			  //goto all page page ="redirect:all"; }
			 
			return page;
		}

	// 6. Update on Edit Form submit
	@PostMapping("/update")
	public String updateForm(@ModelAttribute Employee employee, RedirectAttributes attributes) {
		// update form data
		service.updateEmployee(employee);
		attributes.addAttribute("message", "Employee '" + employee.getEmpId() + "' Updated");
		return "redirect:all";
	}

}
