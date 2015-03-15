package com.uic.security;

import org.springframework.stereotype.Controller;

@Controller("passwodChecker")
public interface PasswordChecker {
	public void setUsername(String username);
	public void setPassword(String password);
	public String getUsername();
	public String getPassword();
	
	public boolean isValid(String user, String pass);
}
