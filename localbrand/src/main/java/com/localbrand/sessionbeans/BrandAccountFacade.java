package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.BrandAccount;

public class BrandAccountFacade extends AbstractFacade<BrandAccount> {

	@Override
	protected void create(Connection con, BrandAccount t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void edit(Connection con, BrandAccount t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected BrandAccount find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<BrandAccount> findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<BrandAccount> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

   
}
