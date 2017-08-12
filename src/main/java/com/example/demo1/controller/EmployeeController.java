package com.example.demo1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.dao.EmployeeRepository;
import com.example.demo1.entity.Employee;

@RestController(value = "/")
public class EmployeeController {
	
    @Autowired
    EmployeeRepository employeeRepository;
	
    @Transactional
    @RequestMapping(value = "populate")
	public void populateEmployees(){
    	System.out.println("*******插入数据***********");
    	Employee employee1 = new Employee();
    	employee1.setEmployeeName("xy");
    	employeeRepository.save(employee1);
    	Employee employee2 = new Employee();
    	employee2.setEmployeeName("test");
    	employeeRepository.save(employee2);
    	System.out.println("*******录入数据成功***********");
    	
		
	}

   /* @RequestMapping(method = RequestMethod.GET, value = "getList/{id}")
	public String getEmployeeName(@PathVariable String id){
    	System.out.println("*******读取数据**********"+id);
		Employee emp = employeeRepository.findOne(id);
		return emp.getEmployeeName();
	}*/

}
