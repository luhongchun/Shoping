package com.example.demo1;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaRepositories
@SpringCloudApplication
@EnableAsync
public class Demo1Application {
	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		System.out.println("*******查询主类***********");
		
	}
}
