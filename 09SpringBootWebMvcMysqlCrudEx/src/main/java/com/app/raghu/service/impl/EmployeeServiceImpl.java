package com.app.raghu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.raghu.entity.Employee;
import com.app.raghu.repo.EmployeeRepository;
import com.app.raghu.service.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeRepository repo;

	public Integer saveEmployee(Employee e) {
		e = repo.save(e);
		return e.getEmpId();
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = repo.findAll();
		return list;
	}

	public Employee getOneemployee(Integer id) {
		Optional<Employee> opt = repo.findById(id);
		if (opt.isPresent()) {
			Employee e = opt.get();
			return e;
		} else
			return null;
	}

	public void deleteEmployee(Integer id) {
		repo.deleteById(id);

	}

	public void updateEmployee(Employee e) {
		repo.save(e);

	}

}
