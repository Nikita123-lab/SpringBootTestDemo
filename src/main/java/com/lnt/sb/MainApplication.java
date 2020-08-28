package com.lnt.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class MainApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/Employee_Info");
		SpringApplication.run(MainApplication.class, args);
		
	}

}
