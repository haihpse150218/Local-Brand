package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.Category;

public class CategoryFacade extends AbstractFacade<Category> {

	@Override
	protected void create(Connection con, Category t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void edit(Connection con, Category t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Category find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Category> findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Category> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

   
    
}
