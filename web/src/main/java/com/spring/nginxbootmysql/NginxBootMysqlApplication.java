package com.spring.nginxbootmysql;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@RestController
public class NginxBootMysqlApplication {

	//@Autowired
	JdbcTemplate template=new JdbcTemplate(getDataSource());
	
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;
	
	public DataSource getDataSource()
	{
		HikariDataSource ds=new HikariDataSource();
		ds.setUsername(userName);
		ds.setPassword(password);
		ds.setDriverClassName(driverClass);
		ds.setJdbcUrl(url);
		
		return ds;
	}
	@PostConstruct
	public void init() throws SQLException
	{
		System.out.println("RETRV");
        getUsers().forEach( e->{
        	System.out.println(e.getEmail());
        });;
	}

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
