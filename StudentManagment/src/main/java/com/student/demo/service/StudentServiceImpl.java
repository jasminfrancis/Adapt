package com.student.demo.service;

import java.util.List;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.demo.constant.ResponseCode;
import com.student.demo.constant.ResponseDescription;
import com.student.demo.constant.ResponseEntity;
import com.student.demo.constant.ResponseMessage;
import com.student.demo.dto.UserEntity;
import com.student.demo.entity.User;
import com.student.demo.repository.StudentDao;
import com.student.demo.utility.PasswordEncription;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired 
	StudentDao studentDao;
	@Override
	public ResponseEntity<?> addStudent(User student) {
		ResponseEntity<?> response=null;
		String password=student.getPassword();
	    String myEncryptedPassword = PasswordEncription.Encription(password);
	    student.setPassword(myEncryptedPassword);
	    System.out.println(myEncryptedPassword ); 
		int user=studentDao.addStudent(student);
		if(user==1) {
			response=new ResponseEntity<>( ResponseCode.SUCCESS, ResponseMessage.SAVE_DATA,ResponseDescription.SAVE_DATA,user);
		}else {
			response=new ResponseEntity<>( ResponseCode.FAILED, ResponseMessage.SAVE_FAILED,ResponseDescription.SAVE_FAILED,user);
		}
		
		return response;
	}
	@Override
	public ResponseEntity<?> listStudent() {
		ResponseEntity<?> response=null;
		
		List<User> userList=studentDao.findAllStudents();
		if(!userList.isEmpty()) {
			response=new ResponseEntity<>( ResponseCode.SUCCESS, ResponseMessage.LIST_DATA,ResponseDescription.LIST_DATA,userList);
		}else {
			response=new ResponseEntity<>( ResponseCode.SUCCESS, ResponseMessage.RECORD_NOT_FOUND,ResponseDescription.RECORD_NOT_FOUND,userList);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<?> deleteStudent(int id) {
		ResponseEntity<?> response=null;
		if(id!=0) {
			User checkExistence=studentDao.findById(id);
			if(checkExistence!=null) {
			int delete=	studentDao.deleteStudent(id);	
			if(delete==1) 
				response=new ResponseEntity<>( ResponseCode.SUCCESS, ResponseMessage.DELETE,ResponseDescription.DELETE);
			else 
				response=new ResponseEntity<>( ResponseCode.FAILED, ResponseMessage.DELETE_STUDENT,ResponseDescription.DELETE_STUDENT);	
			}else {
				response=new ResponseEntity<>( ResponseCode.FAILED, ResponseMessage.RECORD_NOT_FOUND,ResponseDescription.STUDENT_NOT_FOUND);	
			}
		}else {
			response=new ResponseEntity<>( ResponseCode.PARTIAL_CONTENT, ResponseMessage.PARTIAL_CONTENT,ResponseDescription.PARTIAL_CONTENT);
		}
		return response;
	}
	
	@Override
	public ResponseEntity<?> loginUser(User student) {
		ResponseEntity<?> response=null;
		String requestPassword=student.getPassword();
		
	    User userNameCheck=studentDao.loginUserName(student);
	   
	    System.out.println("userNameCheck="+userNameCheck);
	   
	    if(userNameCheck!=null) {

		    String decryptedPassword = PasswordEncription.Decription(userNameCheck.getPassword());
		    System.out.println(decryptedPassword);
		    if(requestPassword.equals(decryptedPassword)) {
		    	response=new ResponseEntity<>( ResponseCode.SUCCESS, ResponseMessage.LOGIN_SUCCESS,ResponseDescription.LOGIN_SUCCESS,userNameCheck);
		    }else {
		    	response=new ResponseEntity<>( ResponseCode.FAILED, ResponseMessage.LOGIN_FAILED,ResponseDescription.LOGIN_FAILED,userNameCheck);
		    }
	    } else
			response=new ResponseEntity<>( ResponseCode.NOT_FOUND, ResponseMessage.NOT_FOUND,ResponseDescription.NOT_FOUND);
		
		return response;
	}
	@Override
	public ResponseEntity<?> listStudents() {
		ResponseEntity<?> response=null;
		List<UserEntity> userList=studentDao.findAllStudents(2);
		if(!userList.isEmpty()) {
			response=new ResponseEntity<>( ResponseCode.SUCCESS, ResponseMessage.LIST_DATA,ResponseDescription.LIST_DATA,userList);
		}else {
			response=new ResponseEntity<>( ResponseCode.SUCCESS, ResponseMessage.RECORD_NOT_FOUND,ResponseDescription.RECORD_NOT_FOUND,userList);
		}
		return response;
	}

}