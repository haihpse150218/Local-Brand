package com.localbrand.service.implement;

import java.util.List;

import com.localbrand.entities.Customer;
import com.localbrand.service.ILogin;
import com.localbrand.sessionbeans.CustomerFacade;

public class LoginService implements ILogin {

	private static CustomerFacade customerFacade = new CustomerFacade();
	
	private Customer findByUsername(String username) {
		Customer returnCustomer = null;
		try {
			List<Customer> list = customerFacade.findAll();
			
			for (Customer customer : list) {
				if ((username.trim()).equalsIgnoreCase(customer.getUsername())) {
					returnCustomer = customer;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnCustomer;
	}

	@Override
	public Customer loginByUsername(String username, String password) throws Exception {
		Customer loginCustomer = findByUsername(username);
		
		if (loginCustomer == null) {
			throw new Exception("Username not found!");
		}
		if (!password.equals(loginCustomer.getPassword().trim())) {
			loginCustomer = null;
			throw new Exception("Incorrect Password!");
		}
		
		return loginCustomer;
	}

}
