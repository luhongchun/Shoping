package com.example.demo1.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo1.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
	
    List<Employee> findAllByEmployeeName(String employeeName);

}
