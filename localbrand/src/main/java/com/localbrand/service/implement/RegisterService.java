package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.Customer;
import com.localbrand.service.IRegister;
import com.localbrand.sessionbeans.CustomerFacade;

public class RegisterService implements IRegister {
	
	private static CustomerFacade customerFacade = new CustomerFacade();

	public Customer findByUsername(String username) {
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
	public void createUser(Customer customer) throws Exception {
		if (findByUsername(customer.getUsername()) != null) {
			throw new Exception("Username already exists!");
		}
		customerFacade.create(customer);
	}

}
