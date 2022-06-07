package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.MembershipTier;
import com.localbrand.entities.Store;

public class StoreFacade extends AbstractFacade<Store> {

	@Override
	protected void create(Connection con, Store t) throws SQLException {
		String sql = "  INSERT INTO [Store] ([Lat],[Lon],[Phone],[Status])\r\n" + 
				"values	(?, ?, ?,?)";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setNString(1, t.getLat());
		ptm.setNString(2, t.getLon());
		ptm.setNString(3, t.getPhone());
		ptm.setBoolean(4, t.getStatus());
		ResultSet rs = ptm.executeQuery();
		
	}

	@Override
	protected void edit(Connection con, Store t) throws SQLException {
		String sql = "UPDATE [Store] \r\n" + 
				"SET [Lat] = ?\r\n" + 
				", [Lon] = ?\r\n" + 
				", [Phone] = ?\r\n" + 
				", [Status] = ?\r\n" + 
				"WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setNString(1, t.getLat());
		ptm.setNString(2, t.getLon());
		ptm.setNString(3, t.getPhone());
		ptm.setBoolean(4, t.getStatus());
		ptm.setInt(5, t.getId());
		ResultSet rs = ptm.executeQuery();
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		String sql = "UPDATE [Store]"
				+ " SET [Status] = ?"
				+ " WHERE [Id] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setBoolean(1, false);
		ptm.setInt(2, (int)id);
		ResultSet rs = ptm.executeQuery();
		
	}

	@Override
	protected Store find(Connection con, Object id) throws SQLException {
		Store store = null;
		String sql = "SELECT [Id]\r\n" + 
				"      ,[Lat]\r\n" + 
				"      ,[Lon]\r\n" + 
				"      ,[Phone]\r\n" +
				"      ,[Status]\r\n" +
				"  FROM [localbrand].[dbo].[Store]\r\n" + 
				"  WHERE [Id] = ?" +
				"  AND [Status] = 1";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, (int)id);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			store = new Store();
			store.setId(rs.getInt("Id"));
			store.setLat(rs.getString("Lat"));
			store.setLon(rs.getString("Lon"));
			store.setPhone(rs.getString("Phone"));
		}
		
		return store;
	}

	@Override
	protected List<Store> findAll(Connection con) throws SQLException {
		List<Store> list = new ArrayList<>();
		String sql = "SELECT TOP 1000 [Id]\r\n" + 
				"      ,[Lat]\r\n" + 
				"      ,[Lon]\r\n" + 
				"      ,[Phone]\r\n" + 
				"      ,[Status]\r\n" +
				"  FROM [localbrand].[dbo].[Store]" +
				"  WHERE [Status] = 1";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			Store store = new Store();
			store.setId(rs.getInt("Id"));
			store.setLat(rs.getString("Lat"));
			store.setLon(rs.getString("Lon"));
			store.setPhone(rs.getString("Phone"));
			list.add(store);
		}
		return list;
	}

	@Override
	protected List<Store> findRange(Connection con, int begin, int end) throws SQLException {
		List<Store> list = new ArrayList<>();
		
		String sql = "  SELECT TOP(?) [Id],[Lat],[Lon],[Phone],[Status] \r\n" + 
				"  FROM [Store] \r\n" + 
				"  EXCEPT SELECT TOP(?) [Id],[Lat],[Lon],[Phone],[Status] \r\n" + 
				"  FROM [Store]\r\n" + 
				"  WHERE [Status] = 1";
		
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
			list.add(store);
		}
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
int result = 0;
		
		String sql = "SELECT COUNT([Id])" +
					" AS [Result]" +
					" FROM [Store]" +
					" WHERE [Status] = 1";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;
	}

   
    
}
