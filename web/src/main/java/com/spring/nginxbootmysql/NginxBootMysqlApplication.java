package com.spring.nginxbootmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NginxBootMysqlApplication {

	@Autowired
	JdbcTemplate template;
	
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;
	
	@PostConstruct
	public void init() throws SQLException
	{
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(userName);
        dataSourceBuilder.password(password);
        dataSourceBuilder.driverClassName(driverClass);
        template.setDataSource(dataSourceBuilder.build()); 
        
        
        
        System.out.println("\n\n\n\n\n RETRV1 \n\n\n\n");
        Connection con=DriverManager.getConnection(url, userName, password);
        System.out.println(con);
        System.out.println("\n\n\n\n\n RETRV2 \n\n\n\n");
        
        DriverManagerDataSource ds=new DriverManagerDataSource(url, userName, password);
        template.setDataSource(ds);
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
