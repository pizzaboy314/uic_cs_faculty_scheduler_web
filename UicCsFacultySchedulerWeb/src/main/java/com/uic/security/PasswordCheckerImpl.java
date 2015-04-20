package com.uic.security;

import org.apache.commons.lang3.StringUtils;

public class PasswordCheckerImpl implements PasswordChecker{
	private String username, password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
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
