package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.Customer;

public class CustomerFacade extends AbstractFacade<Customer> {

	@Override
	protected void create(Connection con, Customer t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void edit(Connection con, Customer t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Customer find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Customer> findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Customer> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

    
    
}
