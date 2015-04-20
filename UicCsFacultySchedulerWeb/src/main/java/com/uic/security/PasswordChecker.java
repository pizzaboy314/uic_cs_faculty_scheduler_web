package com.uic.security;

import org.springframework.stereotype.Component;

@Component("passChecker")
public interface PasswordChecker {
	public boolean isValid(String user, String pass);
}
