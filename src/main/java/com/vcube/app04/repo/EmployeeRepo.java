package com.vcube.app04.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.app04.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
	

}
