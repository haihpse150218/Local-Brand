package com.localbrand.service;

import java.sql.SQLException;

import com.localbrand.entities.Customer;

public interface IRegister {
	public void createUser(Customer customer) throws Exception;
}
