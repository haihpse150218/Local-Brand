package com.localbrand.service.implement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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
					if(password.equalsIgnoreCase(ac.getPassword())) {
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
	public String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
	
	public String encodePass(String password) {
		String encodePass = null;
		MessageDigest digest;
		try {
            digest = MessageDigest.getInstance("SHA-256"); //   
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            encodePass = LoginAdmin.getInstance().bytesToHex(encodedhash);// chuyen sang hexa de luu cho gon
            System.out.println("encode: "+ encodePass);

        } catch (Exception ex) {
            
        }
		return encodePass;
	}

}
