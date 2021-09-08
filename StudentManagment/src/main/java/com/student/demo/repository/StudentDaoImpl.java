package com.student.demo.repository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.student.demo.constant.ConstantValues;
import com.student.demo.dto.UserEntity;
import com.student.demo.entity.User;
import com.student.demo.mapper.UserMapper;
import com.student.demo.service.StudentService;

@Component
@Qualifier("studentDao")
public class StudentDaoImpl implements StudentDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger=LoggerFactory.getLogger(StudentDaoImpl.class);
	@Override
	public int addStudent(User student) {
		logger.info("###addStudent Dao###");
		try {
			int status = 0;	
			if (student.getId() > 0) {
				// update

				String sql = "UPDATE tbl_user SET password=?, first_name=?, last_name=?, "
						+ "role=?, user_name=? WHERE id=?";
				status = jdbcTemplate.update(sql, student.getPassword(), student.getFirstName(), student.getLastName(),
						student.getRole(),student.getUserName(), student.getId());
				logger.info("###addStudent Updated###");
			} else {
				//Insert
				student.setRole(ConstantValues.STUDENT);
				int userName = ThreadLocalRandom.current().nextInt();  
				student.setUserName(student.getFirstName()+userName);
				String query = "INSERT INTO tbl_user(password,first_name,last_name,role,user_name) VALUES(?,?,?,?,?)";
				status = jdbcTemplate.update(query, student.getPassword(), student.getFirstName(),
						student.getLastName(), student.getRole(), student.getUserName());
				logger.info("###addStudent inserted###");
			}
			return status;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public List<User> findAllStudents() {
		String sql = "SELECT * FROM tbl_user";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));

	}

	

	@Override
	public User findById(int id) {
		try {
		String query="select * from tbl_user where id=?";	
		User student=jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<User>(User.class),id);
		return student;
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public int deleteStudent(int id) {
		logger.info("###deleteStudent dao###");
		int status=0;
		try {
			String query="DELETE from tbl_user where id=?";	
			status=jdbcTemplate.update(query,id);
			logger.info("###deleteStudent dao success###");
			return status;
		}catch(Exception e) {
			return status;
		}
		
	}

	@Override
	public User loginUser(User student) {
		logger.info("###loginUser dao###");
		String query="SELECT * from tbl_user where user_name=? and password=?";
		try {
			student= jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<User>(User.class), student.getUserName(),student.getPassword());	   
			return student;
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public List<UserEntity> findAllStudents(int roleId) {
		logger.info("###findAllStudents dao###");
		String query="select s.id,s.password,s.user_name,s.role,s.first_name,s.last_name,r.role_name from tbl_user s left join tbl_role r on r.id=s.role where s.role=?";
		List<UserEntity> list=jdbcTemplate.query(query,new UserMapper(),roleId);
		return list;
	}

	@Override
	public User loginUserName(User user) {
		try {
			user= jdbcTemplate.queryForObject("SELECT * FROM tbl_user WHERE user_name=?", new BeanPropertyRowMapper<User>(User.class), user.getUserName());	   
		return user;
		}catch(Exception e) {
			return null;
		}
		
		
	}

	
}
