package com.uic.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("passChecker")
public class PasswordCheckerImpl implements PasswordChecker{
	private String username, password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("set pass to \"" + password + "\"");
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		System.out.println("set username to \"" + username + "\"");
		this.username = username;
	}

	@Override
	public boolean isValid(String user, String pass) {
		if (StringUtils.equals(username, user) && StringUtils.equals(password, pass)){
			return true;
		}
		return false;
	}
}
