package com.localbrand.service.implement;

import java.sql.SQLException;

import com.localbrand.entities.BrandAccount;
import com.localbrand.service.IHomeAdmin;
import com.localbrand.sessionbeans.BrandAccountFacade;

public class LoginAdmin implements IHomeAdmin {
	private static LoginAdmin instance = new LoginAdmin();
	private LoginAdmin() {}
	public static LoginAdmin getInstance() {
		if(instance == null) {
			instance = new LoginAdmin();
		}
		return instance;
	}
	public BrandAccount CheckAccount(String username, String password) {
		BrandAccountFacade bf = new BrandAccountFacade();
		BrandAccount result = null;
		try {
			for (BrandAccount ac : bf.findAll()) {
				if(ac.getUsername().equalsIgnoreCase(username.trim())){
					if(password.equals(ac.getPassword())) {
						result = ac;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
