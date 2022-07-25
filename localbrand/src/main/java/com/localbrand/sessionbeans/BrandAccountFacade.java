package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.BrandAccount;

public class BrandAccountFacade extends AbstractFacade<BrandAccount> {
	BrandFacade bf = new BrandFacade();
	private void checkNull(BrandAccount t) {
		if (t.getName() == null) {
			t.setName("");
		}
		if (t.getUsername() == null) {
			t.setUsername("");
		}
		if (t.getPassword() == null) {
			t.setPassword("");
		}
		if (t.getRole() == null) {
			t.setRole(false);
		}
	}
	
	@Override
	protected void create(Connection con, BrandAccount t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO [BrandAccount]"
				+ "([Name], [Username], [Password], [Role], [BrandId], [Status])"
				+ "VALUES (?, ?, ?, ?,?,?)";
	
		checkNull(t);
	
		PreparedStatement ptm = con.prepareStatement(sql);

		ptm.setNString(1, t.getName());
		ptm.setString(2, t.getUsername());
		ptm.setString(3, t.getPassword());
		ptm.setBoolean(4, t.getRole());
		ptm.setInt(5, t.getBrandId().getId());
		ptm.setInt(6, t.getStatus());
		ptm.executeUpdate();
	}

	@Override
	protected void edit(Connection con, BrandAccount t) throws SQLException {
		String sql = "UPDATE [BrandAccount] "
				+ "SET [Name] = ?, "
				+ "[Username] = ?, "
				+ "[Password] = ?, "
				+ "[Role] = ? , "
				+ "[Status] = ? , "
				+ "[BrandId] = ? "
				+ "WHERE [Id] = ?";
	
		checkNull(t);
	
		PreparedStatement ptm = con.prepareStatement(sql);
	
		ptm.setNString(1, t.getName());
		ptm.setString(2, t.getUsername());
		ptm.setString(3, t.getPassword());
		ptm.setBoolean(4, t.getRole());
		ptm.setInt(5, t.getStatus());
		ptm.setInt(6, t.getBrandId().getId());
		ptm.setInt(7, t.getId());
		ptm.executeUpdate();
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		String sql = "DELETE FROM [BrandAccount]"
				+ "WHERE [Id] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		
		ptm.executeUpdate();
	}

	@Override
	protected BrandAccount find(Connection con, Object id) throws SQLException {
		BrandAccount brandAccount = null;
		
		String sql = "SELECT * FROM [BrandAccount]" +
					" WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			brandAccount = new BrandAccount();
			brandAccount.setId(rs.getInt("Id"));
			brandAccount.setName(rs.getNString("Name"));
			brandAccount.setUsername(rs.getString("Username"));
			brandAccount.setPassword(rs.getString("Password"));
			brandAccount.setRole(rs.getBoolean("Role"));
			brandAccount.setBrandId(bf.find(rs.getInt("BrandId")));
			brandAccount.setStatus(rs.getInt("Status"));
		}
		return brandAccount;
	}

	@Override
	protected List<BrandAccount> findAll(Connection con) throws SQLException {
		List<BrandAccount> list = new ArrayList<>();
		
		String sql = "SELECT * FROM [BrandAccount]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		
		while (rs.next()) {
			BrandAccount brandAccount = new BrandAccount();
			brandAccount.setId(rs.getInt("Id"));
			brandAccount.setName(rs.getNString("Name"));
			brandAccount.setUsername(rs.getString("Username"));
			brandAccount.setPassword(rs.getString("Password"));
			brandAccount.setRole(rs.getBoolean("Role"));
			brandAccount.setBrandId(bf.find(rs.getInt("BrandId")));
			brandAccount.setStatus(rs.getInt("Status"));
			list.add(brandAccount);
		}
		return list;
	}

	@Override
	protected List<BrandAccount> findRange(Connection con, int begin, int end) throws SQLException {
		List<BrandAccount> list = new ArrayList<>();
		
		String sql = "  SELECT TOP(?) * FROM [BrandAccount]" + 
					" EXCEPT SELECT TOP(?) * FROM [BrandAccount]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			BrandAccount brandAccount = new BrandAccount();
			brandAccount.setId(rs.getInt("Id"));
			brandAccount.setName(rs.getNString("Name"));
			brandAccount.setUsername(rs.getString("Username"));
			brandAccount.setPassword(rs.getString("Password"));
			brandAccount.setRole(rs.getBoolean("Role"));
			brandAccount.setBrandId(bf.find(rs.getInt("BrandId")));
			brandAccount.setStatus(rs.getInt("Status"));
			list.add(brandAccount);
		}
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		int result = 0;
		
		String sql = "SELECT COUNT([Id])" +
					" AS [Result]" +
					" FROM [BrandAccount]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;
	}

   
}
