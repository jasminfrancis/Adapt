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
	
	/**
	 * This service used for creating and editing student
	 * @param student
	 * @return
	 */
	@PostMapping("/addStudent" )
	@ResponseBody
	public ResponseEntity<?> addStudent(@RequestBody User student){
		logger.info("###############addStudent####################");
		 ResponseEntity<?> response=studentService.addStudent(student);
		return response;
	}
	
	/**
	 * This service used for getting studentlist without joining roles
	 * Not used currently
	 * @param student
	 * @return
	 */
	@GetMapping("/listStudent" )
	@ResponseBody
	public ResponseEntity<?> listStudent(){
		 ResponseEntity<?> response=studentService.listStudent();
		return response;
	}
	/**
	 *This service redirect tp login view page
	 * @return
	 */
	@GetMapping("/" )
	public String home(){
		return "login";
	}
	/**
	 * This service is used for delete student
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteStudent/{id}" )
	@ResponseBody
	public ResponseEntity<?> deleteStudent(@PathVariable int id){
		logger.info("#######delete student###########");
		 ResponseEntity<?> response=studentService.deleteStudent(id);
		return response;
	}
	/**
	 * This service is used for login student and admin
	 * @param student
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/login" )
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody User student,RedirectAttributes redirectAttributes){
		
		logger.info("#######login############");
		 ResponseEntity<?> response=studentService.loginUser(student);
	        return response;
	    
	}
	/**
	 * Getting student.jsp view page
	 * @param model
	 * @return
	 */
	@GetMapping("/student" )
	public String home1(Map<String, Object> model){
		return "student";
	}
	/**
	 * This service is used for getting view page welcome.jsp
	 * @return
	 */
	@GetMapping("/welcome/{firstName}" )
	public ModelAndView welcome(@PathVariable("firstName") String firstName){
		logger.info("#######welcome############");
		ModelAndView mv=new ModelAndView();
		mv.addObject("msg","Welcome  "+  firstName +",    You have successfully logged in");
		mv.setViewName("welcome");
		return mv;
	}
/**
 * This service is used for getting student List with mapping role table
 * @return
 */
	@GetMapping("/studentList" )
	@ResponseBody
	public ResponseEntity<?> studentList(){
		logger.info("#######studentList############");
		 ResponseEntity<?> response=studentService.listStudents();
		return response;
	}
}
