package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Store;

public class BrandFacade extends AbstractFacade<Brand> {

    private void checkNull(Brand t) {
		if (t.getStatus() == null) {
			t.setStatus("Active");
		}
		
	}
	
	@Override
	protected void create(Connection con, Brand t) throws SQLException {
		String sql = "  INSERT INTO [Brand] ([Name],[Description],[Status])\r\n" + 
				"values	(?, ?, ?)";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		ptm.setString(3, t.getStatus());
		
		ptm.executeUpdate();
		
	}

	@Override
	protected void edit(Connection con, Brand t) throws SQLException {
		String sql = "UPDATE [Brand] \r\n" + 
				"SET [Name] = ?\r\n" + 
				", [Description] = ?\r\n" + 
				", [Status] = ?\r\n" + 
				"WHERE [Id] = ?";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		ptm.setString(3, t.getStatus());

		ptm.setInt(4, t.getId());
		ptm.executeUpdate();
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		
		//????
		
		String sql = "DELETE FROM [Brand]"
				+ " WHERE [Id] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ptm.executeUpdate();
		
	}

	@Override
	protected Brand find(Connection con, Object id) throws SQLException {
		Brand brand = null;
		
		String sql = "SELECT * FROM [Brand]\r\n" + 
				"  WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			brand = new Brand();
			brand.setId(rs.getInt("Id"));
			brand.setName(rs.getNString("Name"));
			brand.setDescription(rs.getNString("Description"));
			brand.setStatus(rs.getString("Status"));
			
		}
		
		return brand;
		
	}

	@Override
	protected List<Brand> findAll(Connection con) throws SQLException {
		List<Brand> list = new ArrayList<>();
		String sql = "SELECT * FROM [Brand]";
		
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while (rs.next()) {
			Brand brand = new Brand();
			brand = new Brand();
			brand.setId(rs.getInt("Id"));
			brand.setName(rs.getNString("Name"));
			brand.setDescription(rs.getNString("Description"));
			brand.setStatus(rs.getString("Status"));
			
			list.add(brand);
		}
		
		return list;
	
	}

	@Override
	protected List<Brand> findRange(Connection con, int begin, int end) throws SQLException {
		List<Brand> list = new ArrayList<>();
		
		String sql = "  SELECT TOP(?) * FROM [Brand] \r\n" + 
				"  EXCEPT SELECT TOP(?) * FROM [Brand]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			Brand brand = new Brand();
			brand = new Brand();
			brand.setId(rs.getInt("Id"));
			brand.setName(rs.getNString("Name"));
			brand.setDescription(rs.getNString("Description"));
			brand.setStatus(rs.getString("Status"));
			
			list.add(brand);
		}
		
		return list;
	
	}

	@Override
	protected int count(Connection con) throws SQLException {
		int result = 0;
		
		String sql = "SELECT COUNT([Id])" +
					" AS [Result]" +
					" FROM [Brand]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;	
		
	}

    
}
