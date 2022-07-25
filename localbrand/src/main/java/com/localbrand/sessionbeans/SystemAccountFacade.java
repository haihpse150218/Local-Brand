package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.SystemAccount;

public class SystemAccountFacade extends AbstractFacade<SystemAccount>{
	@Override
	protected void create(Connection con, SystemAccount sa) throws SQLException {
		
	}
	@Override
	protected void edit(Connection con, SystemAccount sa) throws SQLException {
		
	}
	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		
	}
	@Override
	protected SystemAccount find(Connection con, Object id) throws SQLException {
		SystemAccount sa = null;
		String sql = "SELECT [Id],[Username],[Name],[Password] FROM [localbrand].[dbo].[SystemAccount] WHERE [Id]=?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, (int)id);
		ResultSet rs = ptm.executeQuery();
		if (rs.next()) {
			sa =  new SystemAccount();
			sa.setId(rs.getInt("Id"));
			sa.setUsername(rs.getString("Username"));
			sa.setName(rs.getString("Name"));
			sa.setPassword(rs.getString("Password"));
		}
		return sa;
	}
	@Override
	protected List<SystemAccount> findAll(Connection con) throws SQLException {
		List<SystemAccount> list = new ArrayList<>();
		String sql = "SELECT [Id], [Username], [Name], [Password] FROM [localbrand].[dbo].[SystemAccount];";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			SystemAccount sa = new SystemAccount();
			sa.setId(rs.getInt("Id"));
			sa.setUsername(rs.getString("Username"));
			sa.setName(rs.getString("Name"));
			sa.setPassword(rs.getString("Password"));
			list.add(sa);
		}
		return list;
	}
	@Override
	protected List<SystemAccount> findRange(Connection con, int begin, int end) throws SQLException {
		List<SystemAccount> list = new ArrayList<>();
		return list;
	}
	@Override
	protected int count(Connection con) throws SQLException {
		int result = 0;
		return result;
	}
}
