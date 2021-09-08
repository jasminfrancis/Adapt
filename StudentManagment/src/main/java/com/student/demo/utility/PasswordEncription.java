package com.student.demo.utility;

import org.jasypt.util.text.AES256TextEncryptor;

public class PasswordEncription {
	public static final String secret="admin@123";
	public static String Encription(String password) {
		AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
	    aesEncryptor.setPassword(secret);
	    String myEncryptedPassword = aesEncryptor.encrypt(password);
	    return myEncryptedPassword;
	}
	public static String Decription(String password) {
		AES256TextEncryptor aesEncryptor1 = new AES256TextEncryptor();
	    aesEncryptor1.setPassword(secret);
	    String decryptedPassword = aesEncryptor1.decrypt(password);
	    return decryptedPassword;
	}

}
