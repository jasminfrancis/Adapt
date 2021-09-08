package com.student.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.jdbc.core.RowMapper;

import com.student.demo.dto.UserEntity;
import com.student.demo.utility.PasswordEncription;


public class UserMapper implements RowMapper<UserEntity>  {

	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserEntity userEntity=new UserEntity();
		userEntity.setId(rs.getInt("id"));
		userEntity.setFirstName(rs.getString("first_name"));
		userEntity.setLastName(rs.getString("last_name"));
		userEntity.setRole(rs.getInt("role"));
		userEntity.setUserName(rs.getString("user_name"));
		userEntity.setPassword(rs.getString("password"));
		String password=rs.getString("password");
		String decryptedPassword = PasswordEncription.Decription(password);
	    userEntity.setPassword(decryptedPassword);
		userEntity.setRoleName(rs.getString("role_name"));
		return userEntity;
	}

} 
