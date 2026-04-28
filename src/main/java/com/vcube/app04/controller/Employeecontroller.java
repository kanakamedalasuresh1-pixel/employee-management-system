package com.vcube.app04.controller;


import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;

import com.vcube.app04.model.Employee;
import com.vcube.app04.repo.EmployeeRepo;

import jakarta.validation.Valid;

@RestController
public class Employeecontroller {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	
	@PostMapping("/insertEmp")
	public Employee insertEmployee(@Valid @RequestBody Employee emp) {
	    return employeeRepo.save(emp);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
	    return employeeRepo.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
	    return employeeRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Employee not found"));
	}
	
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee emp) {
	    Employee existing = employeeRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Employee not found"));
	    existing.setEname(emp.getEname());
	    existing.setAge(emp.getAge());
	    existing.setSalary(emp.getSalary());
	    existing.setDepartment(emp.getDepartment());
	    existing.setRole(emp.getRole());
	    existing.setEmail(emp.getEmail());
	    existing.setPhoneNumber(emp.getPhoneNumber());
	    existing.setAddress(emp.getAddress());
	    existing.setJoiningDate(emp.getJoiningDate());
		return employeeRepo.save(existing);
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id) {
	    employeeRepo.deleteById(id);
	    return "Employee deleted successfully";
	}
	
	@PatchMapping("/employees/{id}")
	public Employee patchEmployee(@PathVariable int id,
	                              @RequestBody Employee emp) {

	    Employee existing = employeeRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Employee not found"));

	    if (emp.getEname() != null) {
	        existing.setEname(emp.getEname());
	    }

	    if (emp.getSalary() != null) {
	        existing.setSalary(emp.getSalary());
	    }

	    if (emp.getJoiningDate() != null) {
	        existing.setJoiningDate(emp.getJoiningDate());
	    }

	    if (emp.getAddress() != null) {
	        existing.setAddress(emp.getAddress());
	    }

	    if (emp.getDepartment() != null) {
	        existing.setDepartment(emp.getDepartment());
	    }

	    if (emp.getRole() != null) {
	        existing.setRole(emp.getRole());
	    }

	    if (emp.getEmail() != null) {
	        existing.setEmail(emp.getEmail());
	    }

	    if (emp.getPhoneNumber() != null) {
	        existing.setPhoneNumber(emp.getPhoneNumber());
	    }

	    return employeeRepo.save(existing);
	}
	
	@GetMapping("/employees/page")
	public Page<Employee> getPageable(
	        @RequestParam int page,
	        @RequestParam int size) {

	    PageRequest pageable = PageRequest.of(page, size);
	    return employeeRepo.findAll(pageable);
	}
	
	
	@GetMapping("/employees/salarySort")
	public List<Employee> sortBySalary(@RequestParam String direction) {

	    Sort sort;

	    if (direction.equalsIgnoreCase("asc")) {
	        sort = Sort.by("salary").ascending();
	    } else {
	        sort = Sort.by("salary").descending();
	    }

	    return employeeRepo.findAll(sort);
	}
	}
