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

public class StoreFacade extends AbstractFacade<Store> {

	private void checkNull(Store t) {
		if (t.getStatus() == null) {
			t.setStatus(true);
		}
	}
	
	@Override
	protected void create(Connection con, Store t) throws SQLException {
		String sql = "  INSERT INTO [Store] ([Lat],[Lon],[Phone],[Status],[BrandId])\r\n" + 
				"values	(?, ?, ?, ?, ?)";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1, t.getLat());
		ptm.setString(2, t.getLon());
		ptm.setString(3, t.getPhone());
		ptm.setBoolean(4, t.getStatus());
		
		ptm.setInt(5, t.getBrandId().getId());
		
		ptm.executeUpdate();		
	}

	@Override
	protected void edit(Connection con, Store t) throws SQLException {
		String sql = "UPDATE [Store] \r\n" + 
				"SET [Lat] = ?\r\n" + 
				", [Lon] = ?\r\n" + 
				", [Phone] = ?\r\n" + 
				", [Status] = ?\r\n" + 
				", [BrandId] = ?\r\n" + 
				"WHERE [Id] = ?";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1, t.getLat());
		ptm.setString(2, t.getLon());
		ptm.setString(3, t.getPhone());
		ptm.setBoolean(4, t.getStatus());
		
		ptm.setInt(5, t.getBrandId().getId());
		
		ptm.setInt(6, t.getId());
		ptm.executeUpdate();
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		String sql = "DELETE FROM [Store]"
				+ " WHERE [Id] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ptm.executeUpdate();
		
	}

	@Override
	protected Store find(Connection con, Object id) throws SQLException {
		Store store = null;
		
		String sql = "SELECT * FROM [Store]\r\n" + 
				"  WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			store = new Store();
			store.setId(rs.getInt("Id"));
			store.setLat(rs.getString("Lat"));
			store.setLon(rs.getString("Lon"));
			store.setPhone(rs.getString("Phone"));
			store.setStatus(rs.getBoolean("Status"));
			
			Brand brand = new Brand();
			brand.setId(rs.getInt("BrandId"));
			store.setBrandId(brand);
			
		}
		
		return store;
		
	}

	@Override
	protected List<Store> findAll(Connection con) throws SQLException {
		List<Store> list = new ArrayList<>();
		String sql = "SELECT * FROM [Store]";
		
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while (rs.next()) {
			Store store = new Store();
			store.setId(rs.getInt("Id"));
			store.setLat(rs.getString("Lat"));
			store.setLon(rs.getString("Lon"));
			store.setPhone(rs.getString("Phone"));
			store.setStatus(rs.getBoolean("Status"));
			
			Brand brand = new Brand();
			brand.setId(rs.getInt("BrandId"));
			store.setBrandId(brand);
			
			list.add(store);
		}
		
		return list;
		
	}

	@Override
	protected List<Store> findRange(Connection con, int begin, int end) throws SQLException {
		List<Store> list = new ArrayList<>();
		
		String sql = "  SELECT TOP(?) * FROM [Store] \r\n" + 
				"  EXCEPT SELECT TOP(?) * FROM [Store]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			Store store = new Store();
			store.setId(rs.getInt("Id"));
			store.setLat(rs.getString("Lat"));
			store.setLon(rs.getString("Lon"));
			store.setPhone(rs.getString("Phone"));
			store.setStatus(rs.getBoolean("Status"));
			
			Brand brand = new Brand();
			brand.setId(rs.getInt("BrandId"));
			store.setBrandId(brand);
			
			list.add(store);
		}
		
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		int result = 0;
		
		String sql = "SELECT COUNT([Id])" +
					" AS [Result]" +
					" FROM [Store]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;
		
	}

   
    
}
