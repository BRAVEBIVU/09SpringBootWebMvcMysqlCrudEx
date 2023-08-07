package com.app.raghu.service;

import java.util.List;

import com.app.raghu.entity.Employee;

public interface IEmployeeService {
	Integer saveEmployee(Employee e);

	List<Employee> getAllEmployees();

	Employee getOneemployee(Integer id);

	void deleteEmployee(Integer id);

	void updateEmployee(Employee e);
}
