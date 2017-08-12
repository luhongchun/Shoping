package com.example.demo1.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "t_employe")
public class Employee {
	
	@Id
    @GeneratedValue(generator = "uuidGenerator")
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	private String id;
	
    private String employeeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}

