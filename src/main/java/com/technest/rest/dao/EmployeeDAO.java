package com.technest.rest.dao;

import org.springframework.stereotype.Repository;

import com.technest.rest.model.Employee;
import com.technest.rest.model.Employees;

@Repository
public class EmployeeDAO {
	private static Employees list = new Employees();

	static {
		list.getEmployeeList().add(new Employee(1, "Lokesh", "Gupta", "technest@gmail.com"));
		list.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
		list.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
	}

	public Employees getAllEmployees() {
		return list;
	}

	public Employee getByName(String name) {
		for (Employee acc : list.getEmployeeList()) {
			if (acc.getFirstName().equals(name))
				return acc;
		}
		return null;
	}

	public void addEmployee(Employee employee) {
		list.getEmployeeList().add(employee);
	}

	public boolean updateEmployee(int id, Employee employee) {
		for (Employee acc : list.getEmployeeList()) {
			if (acc.getId() == id) {
				acc.setAll(id, employee.getFirstName(), employee.getLastName(), employee.getEmail());
				return true;
			}
		}
		return false;
	}
}
