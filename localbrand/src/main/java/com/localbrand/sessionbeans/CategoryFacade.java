package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Category;

public class CategoryFacade extends AbstractFacade<Category> {
	
	private void checkNull(Category t) {
		if (t.getGender() == null) {
			t.setGender(2);
		}
		if (t.getStatus() == null) {
			t.setStatus(true);
		}
	}

	@Override
	protected void create(Connection con, Category t) throws SQLException {
		
		String sql = "INSERT INTO [Category]"
					+ "([Name], [Description], [Gender], [Status])"
					+ "VALUES (?, ?, ?, ?)";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);

		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		if(t.getGender() == null) {
			t.setGender(0);
		}
		ptm.setInt(3, t.getGender());

		ptm.setBoolean(4, t.getStatus());
		

		ptm.executeUpdate();
	}

	@Override
	protected void edit(Connection con, Category t) throws SQLException {
		
		String sql = "UPDATE [Category] "
					+ "SET [Name] = ?, "
					+ "[Description] = ?, "
					+ "[Gender] = ?, "
					+ "[Status] = ? "
					+ "WHERE [Id] = ?";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);
		
		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		ptm.setInt(3, t.getGender());
		ptm.setBoolean(4, t.getStatus());
		ptm.setInt(5, t.getId());
		
		ptm.executeUpdate();
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {

		String sql = "DELETE FROM [Category]"
					+ "WHERE [Id] = ?";
		PreparedStatement ptm = con.prepareStatement(sql);

		ptm.setInt(1, Integer.parseInt(id.toString()));
		

		ptm.executeUpdate();
	}

	@Override
	protected Category find(Connection con, Object id) throws SQLException {
		Category returnCategory = null;
		

		String sql = "SELECT * FROM [Category]" +
					" WHERE [Id] = ?";
		

		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
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
		
		String sql = "SELECT * FROM [Category]";
		

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
		
		String sql = "  SELECT TOP(?) * FROM [Category]" + 
					" EXCEPT SELECT TOP(?) * FROM [Category]";
		
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
					" FROM [Category]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;
	}
}
