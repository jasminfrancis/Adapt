package com.student.demo.utility;

import org.jasypt.util.text.AES256TextEncryptor;

public class PasswordEncription {
	public static String Encription(String password) {
		AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
	    aesEncryptor.setPassword("admin@123");
	    String myEncryptedPassword = aesEncryptor.encrypt(password);
	    return myEncryptedPassword;
	}
	public static String Decription(String password) {
		AES256TextEncryptor aesEncryptor1 = new AES256TextEncryptor();
	    aesEncryptor1.setPassword("admin@123");
	    String decryptedPassword = aesEncryptor1.decrypt(password);
	    return decryptedPassword;
	}

}
