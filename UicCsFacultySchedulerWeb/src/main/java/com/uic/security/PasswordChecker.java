package com.uic.security;

import org.springframework.stereotype.Controller;

public interface PasswordChecker {
	
	public boolean isValid(String user, String pass);
}
