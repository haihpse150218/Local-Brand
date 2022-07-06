package com.localbrand.service;

import com.localbrand.entities.BrandAccount;

public interface ILoginAdmin {
	public BrandAccount CheckAccount(String username, String password);
	
}
