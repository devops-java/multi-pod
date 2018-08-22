package com.spring.nginxbootmysql;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@RestController
public class NginxBootMysqlApplication {

	@Autowired
	JdbcTemplate template;
	
	
	@Autowired
	HikariDataSource ds;
	
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
		System.out.println(url);
		System.out.println(userName);
		System.out.println(password);
		System.out.println(driverClass);
		System.out.println("INIT");
       
	}

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(NginxBootMysqlApplication.class, args);
	
		Arrays.stream(context.getBeanDefinitionNames()).forEach(e->{
			System.out.println(e);
		});
	}

	@GetMapping("/hello")
	public String getHello() {
		return "Hello from Spring boot";
	}

	@GetMapping("/users/{host}")
	public List<User> getUsers(@PathVariable("host") String host) {
		JdbcTemplate template=new JdbcTemplate(getDataSource(host));
		//template.setDataSource(getDataSource(host));
		return template.query("SELECT * FROM TBL_USER", new UserRowMapper());
	}

	private DataSource getDataSource(String host) {
		// TODO Auto-generated method stub
		
		System.out.println("host: "+host);
		String jdbcUrl=url.replace("misql", host);
		System.out.println(jdbcUrl);
		
		DriverManagerDataSource ds=new DriverManagerDataSource(jdbcUrl,userName,password);
		
		System.out.println(ds.getClass());
		
		return ds;
	}
}
