package com.localbrand.service;

import com.localbrand.entities.Customer;

public interface ILogin {
	public Customer loginByUsername(String username, String password) throws Exception;
}
