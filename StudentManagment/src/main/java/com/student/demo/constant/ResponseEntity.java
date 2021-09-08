package com.student.demo.constant;

public class ResponseEntity<T> {
	private int status;
	private String message;
	private String description;
	private T response;
	
	
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ResponseEntity(int status, String message, String description) {
		super();
		this.status = status;
		this.message = message;
		this.description = description;
	}
	public ResponseEntity(int status, String message, String description, T response) {
		super();
		this.status = status;
		this.message = message;
		this.description = description;
		this.response = response;
	}
	
	
	
	

}
