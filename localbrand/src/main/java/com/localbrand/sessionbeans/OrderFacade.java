package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.Order;

public class OrderFacade extends AbstractFacade<Order> {

	@Override
	protected void create(Connection con, Order t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void edit(Connection con, Order t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Order find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Order> findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Order> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

   
    
}
