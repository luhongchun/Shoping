package com.example.demo1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

//import com.example.demo1.entity.Employee;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Demo1Application.class)
@WebAppConfiguration
public class Demo1ApplicationTests {

	@Before
	public void setup(){
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Autowired
	RestTemplate restTemplate;
	 /* @Test
  public void testPost() throws Exception {
    	System.out.println("*******testPost***********");
		Employee entity = new Employee();
		Employee e = restTemplate.postForObject("http://218.61.208.68:8008/test",
                            entity, Employee.class);
		System.out.println("*******testPost***********");
    }*/
}
