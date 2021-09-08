package com.student.demo.service;

import com.student.demo.constant.ResponseEntity;
import com.student.demo.entity.User;

public interface StudentService {

	ResponseEntity<?> addStudent(User student);

	ResponseEntity<?> listStudent();

	

	ResponseEntity<?> deleteStudent(int id);

	ResponseEntity<?> loginUser(User student);

	ResponseEntity<?> listStudents();

}
