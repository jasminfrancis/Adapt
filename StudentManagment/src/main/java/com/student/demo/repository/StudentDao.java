package com.student.demo.repository;

import java.util.List;

import com.student.demo.dto.UserEntity;
import com.student.demo.entity.User;

public interface StudentDao {

	int addStudent(User student);

	User findById(int id);

	int deleteStudent(int id);

	User loginUser(User student);

	List<UserEntity> findAllStudents(int i);

	User loginUserName(User userName);

}
