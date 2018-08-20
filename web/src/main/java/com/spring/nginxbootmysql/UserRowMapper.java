package com.spring.nginxbootmysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(rs.getInt("USER_ID"));
		user.setEmail(rs.getString("EMAIL"));
		user.setPassword(rs.getString("PASSWORD"));

		return user;
	}

}
