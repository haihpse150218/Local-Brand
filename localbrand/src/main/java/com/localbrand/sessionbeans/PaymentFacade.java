package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.Payment;

public class PaymentFacade extends AbstractFacade<Payment> {

	@Override
	protected void create(Connection con, Payment t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void edit(Connection con, Payment t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Payment find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Payment> findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Payment> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

   
    
}
