package com.student.demo.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.student.demo.constant.ResponseEntity;
import com.student.demo.entity.User;

import com.student.demo.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
    StudentService studentService;
	private static final Logger logger=LoggerFactory.getLogger(StudentController.class);
	
	@PostMapping("/addStudent" )
	@ResponseBody
	public ResponseEntity<?> addStudent(@RequestBody User student){
		logger.info("###############addStudent####################");
		 ResponseEntity<?> response=studentService.addStudent(student);
		return response;
	}
	
	@GetMapping("/listStudent" )
	@ResponseBody
	public ResponseEntity<?> listStudent(){
		 ResponseEntity<?> response=studentService.listStudent();
		return response;
	}
	
	@GetMapping("/" )
	public String home(){
		
		return "login";
	}
	
	@DeleteMapping("/deleteStudent/{id}" )
	@ResponseBody
	public ResponseEntity<?> deleteStudent(@PathVariable int id){
		 ResponseEntity<?> response=studentService.deleteStudent(id);
		return response;
	}
	
	@PostMapping("/login" )
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody User student,RedirectAttributes redirectAttributes){
		
		 System.out.println("-----------------------");
		 ResponseEntity<?> response=studentService.loginUser(student);
	        return response;
	    
	}
	@GetMapping("/student" )
	public String home1(Map<String, Object> model){
		System.out.println("********************");
		return "student";
	}
	@GetMapping("/welcome/{firstName}" )
	public ModelAndView welcome(@PathVariable("firstName") String firstName){
		System.out.println("********************");
		ModelAndView mv=new ModelAndView();
		mv.addObject("msg","Welcome  "+  firstName +",    You have successfully logged in");
		mv.setViewName("welcome");
		return mv;
	}

	@GetMapping("/listStudents" )
	@ResponseBody
	public ResponseEntity<?> listStudents(){
		 ResponseEntity<?> response=studentService.listStudents();
		return response;
	}
}
