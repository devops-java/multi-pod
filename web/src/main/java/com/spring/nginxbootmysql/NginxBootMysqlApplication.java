package com.spring.nginxbootmysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NginxBootMysqlApplication {

	@Autowired
	JdbcTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(NginxBootMysqlApplication.class, args);
	}

	@GetMapping("/hello")
	public String getHello() {
		return "Hello from Spring boot";
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return template.query("SELECT * FROM TBL_USER", new UserRowMapper());
	}
}
