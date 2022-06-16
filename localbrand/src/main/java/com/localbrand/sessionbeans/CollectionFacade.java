package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Collection;

public class CollectionFacade extends AbstractFacade<Collection> {

	@Override
	protected void create(Connection con, Collection t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO [dbo].[Collection]\r\n" + 
				"           ([Name]\r\n" + 
				"           ,[Description]\r\n" + 
				"           ,[ImageUrl]\r\n" + 
				"           ,[Status]\r\n" + 
				"           ,[BrandId])\r\n" + 
				"     VALUES (?,?,?,?,?);"; 
		PreparedStatement ptm = con.prepareStatement(sql);
		int bid =t.getBrandId().getId();
		ptm.setString(1, t.getName());
		ptm.setString(2, t.getDescription());
		ptm.setString(3, t.getImageUrl());
		ptm.setInt(4, t.getStatus());
		ptm.setInt(5,bid);
		ptm.executeUpdate();
	}

	@Override
	protected void edit(Connection con, Collection t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE [dbo].[Collection]\r\n" + 
				"   SET [Name] = ?\r\n" + 
				"      ,[Description] = ?\r\n" + 
				"      ,[ImageUrl] = ?\r\n" + 
				"      ,[Status] = ?\r\n" + 
				"      ,[BrandId] = ?\r\n" + 
				"  WHERE [Id]=?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1, t.getName());
		ptm.setString(2, t.getDescription());
		ptm.setString(3, t.getImageUrl());
		ptm.setInt(4, t.getStatus());
		ptm.setInt(5, t.getBrandId().getId());
		ptm.setInt(6, t.getId());
		ptm.executeUpdate();
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM [dbo].[Collection]\r\n" + 
				"      WHERE [Id]=?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ptm.executeUpdate();
	}

	@Override
	protected Collection find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		Collection collection = null;
		String sql = "SELECT [Id]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Description]\r\n" + 
				"      ,[ImageUrl]\r\n" + 
				"      ,[Status]\r\n" + 
				"      ,[BrandId]\r\n" + 
				"  FROM [dbo].[Collection]\r\n" + 
				"  WHERE [Id]=?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ResultSet rs = ptm.executeQuery();
		if (rs.next()) {
			Brand bid =new Brand();
			collection = new Collection();
			bid.setId(rs.getInt("BrandId"));
			collection.setId(rs.getInt("Id"));
			collection.setName(rs.getString("Name"));
			collection.setDescription("Description");
			collection.setImageUrl(rs.getString("ImageUrl"));
			collection.setStatus(rs.getInt("Status"));
			bid.setId(rs.getInt("BrandId"));
			collection.setBrandId(bid);
		}
		return collection;
	}

	@Override
	protected List<Collection> findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		List<Collection> list = new ArrayList<>();
		Collection collection = null;
		Brand bid = null;
		String sql = "SELECT [Id]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Description]\r\n" + 
				"      ,[ImageUrl]\r\n" + 
				"      ,[Status]\r\n" + 
				"      ,[BrandId]\r\n" + 
				"  FROM [dbo].[Collection];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		while (rs.next()) {
			bid =new Brand();
			collection = new Collection();
			collection.setId(rs.getInt("Id"));
			collection.setName(rs.getString("Name"));
			collection.setDescription("Description");
			collection.setImageUrl(rs.getString("ImageUrl"));
			collection.setStatus(rs.getInt("Status"));
			bid.setId(rs.getInt("BrandId"));
			collection.setBrandId(bid);
			list.add(collection);
		}
		return list;
	}

	@Override
	protected List<Collection> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		List<Collection> list = new ArrayList<>();
		Collection collection = null;
		Brand bid = null;
		String sql = "SELECT TOP (?) [Id]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Description]\r\n" + 
				"      ,[ImageUrl]\r\n" + 
				"      ,[Status]\r\n" + 
				"      ,[BrandId]\r\n" + 
				"  FROM [localbrand].[dbo].[Collection]\r\n" + 
				"EXCEPT SELECT TOP (?) [Id]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Description]\r\n" + 
				"      ,[ImageUrl]\r\n" + 
				"      ,[Status]\r\n" + 
				"      ,[BrandId]\r\n" + 
				"  FROM [localbrand].[dbo].[Collection];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		while (rs.next()) {
			bid =new Brand();
			collection = new Collection();
			collection.setId(rs.getInt("Id"));
			collection.setName(rs.getString("Name"));
			collection.setDescription("Description");
			collection.setImageUrl(rs.getString("ImageUrl"));
			collection.setStatus(rs.getInt("Status"));
			bid.setId(rs.getInt("BrandId"));
			collection.setBrandId(bid);
			list.add(collection);
		}
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "SELECT COUNT([Id])\r\n" + 
				"AS [Result]\r\n" + 
				"FROM [dbo].[Collection];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		return result;
	}

}
