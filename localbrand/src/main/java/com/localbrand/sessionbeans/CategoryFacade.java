package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Category;

public class CategoryFacade extends AbstractFacade<Category> {

	@Override
	protected void create(Connection con, Category t) throws SQLException {
		
		String sql = "INSERT INTO [Category]"
					+ "([Name], [Description], [Gender], [Status])"
					+ "VALUES (?, ?, ?, 1)";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		ptm.setInt(3, t.getGender());
		
		ptm.executeUpdate();
	}

	@Override
	protected void edit(Connection con, Category t) throws SQLException {
		
		String sql = "UPDATE [Category] "
					+ "SET [Name] = ?, "
					+ "[Description] = ?, "
					+ "[Gender] = ? "
					+ "WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		ptm.setInt(3, t.getGender());
		ptm.setInt(4, t.getId());
		
		ptm.executeUpdate();
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {

		String sql = "UPDATE [Category]"
					+ "SET [Status] = 0"
					+ "WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, (int)id);
		
		ptm.executeUpdate();
	}

	@Override
	protected Category find(Connection con, Object id) throws SQLException {
		Category returnCategory = null;
		
		String sql = "SELECT [Id]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Description]\r\n" + 
				"      ,[Gender]\r\n" + 
				"      ,[Status]\r\n" + 
				"  	FROM [localbrand].[dbo].[Category]" +
				"	WHERE [Id] = ?" +
				"	AND [Status] = 1";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, (int)id);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			returnCategory = new Category();
			returnCategory.setId(rs.getInt("Id"));
			returnCategory.setName(rs.getNString("Name"));
			returnCategory.setDescription(rs.getNString("Description"));
			returnCategory.setGender(rs.getInt("Gender"));
			returnCategory.setStatus(rs.getBoolean("Status"));
		}
		
		return returnCategory;
	}

	@Override
	protected List<Category> findAll(Connection con) throws SQLException {
		
		List<Category> list = new ArrayList<>();
		
		String sql = "SELECT [Id]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Description]\r\n" + 
				"      ,[Gender]\r\n" + 
				"      ,[Status]\r\n" + 
				"  	FROM [localbrand].[dbo].[Category]" +
				"	WHERE [Status] = 1";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			Category nextCategory = new Category();
			nextCategory.setId(rs.getInt("Id"));
			nextCategory.setName(rs.getNString("Name"));
			nextCategory.setDescription(rs.getNString("Description"));
			nextCategory.setGender(rs.getInt("Gender"));
			nextCategory.setStatus(rs.getBoolean("Status"));
			list.add(nextCategory);
		}
		return list;
	}

	@Override
	protected List<Category> findRange(Connection con, int begin, int end) throws SQLException {
		
		List<Category> list = new ArrayList<>();
		
		String sql = "  select top(?) * from [dbo].[Category]\r\n" + 
				"  except select top(?) * from [dbo].[Category]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			Category nextCategory = new Category();
			nextCategory.setId(rs.getInt("Id"));
			nextCategory.setName(rs.getNString("Name"));
			nextCategory.setDescription(rs.getNString("Description"));
			nextCategory.setGender(rs.getInt("Gender"));
			nextCategory.setStatus(rs.getBoolean("Status"));
			list.add(nextCategory);
		}
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		
		int result = 0;
		
		String sql = "SELECT COUNT([Id])" +
					" AS [Result]" +
					" FROM [Category]" +
					" WHERE [Status] = 1";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;
	}
}
