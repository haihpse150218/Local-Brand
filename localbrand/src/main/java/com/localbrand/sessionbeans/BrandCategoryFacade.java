package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.BrandCategoryPK;

public class BrandCategoryFacade extends AbstractFacade<BrandCategory> {

	private void checkNull(BrandCategory t) {
		if (t.getName() == null) {
			t.setName("");
		}
	}
	@Override
	protected void create(Connection con, BrandCategory t) throws SQLException {
		String sql = "INSERT INTO [BrandCategory]"
					+ "([CateId], [BrandId], [Name], [Description], [Status])"
					+ "VALUES (?, ?, ?, ?, ?)";
	
		checkNull(t);
	
		PreparedStatement ptm = con.prepareStatement(sql);

		ptm.setInt(1, t.getBrandCategoryPK().getCateId());
		ptm.setInt(2, t.getBrandCategoryPK().getBrandId());
		ptm.setNString(3, t.getName());
		ptm.setNString(4, t.getDescription());
		ptm.setBoolean(5, t.getStatus());
	
		ptm.executeUpdate();
	}

	@Override
	protected void edit(Connection con, BrandCategory t) throws SQLException {
		String sql = "UPDATE [BrandCategory] "
					+ "SET [Name] = ?, "
					+ "[Description] = ?, "
					+ "[Status] = ? "
					+ "WHERE [CateId] = ? "
					+ "AND [BrandId] = ?";
	
		checkNull(t);
	
		PreparedStatement ptm = con.prepareStatement(sql);
	
		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		ptm.setBoolean(3, t.getStatus());
		ptm.setInt(4, t.getBrandCategoryPK().getCateId());
		ptm.setInt(5, t.getBrandCategoryPK().getBrandId());
	
		ptm.executeUpdate();
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		String sql = "DELETE FROM [BrandCategory]"
					+ "WHERE [CateId] = ? "
					+ "AND [BrandId] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
	
		ptm.executeUpdate();
	}

	@Override
	protected BrandCategory find(Connection con, Object id) throws SQLException {
		BrandCategory nextBC = null;
		
		String sql = "SELECT * FROM [Category]" +
					" WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			nextBC = new BrandCategory();
			BrandCategoryPK nextPK = new BrandCategoryPK(rs.getInt("CateId"), rs.getInt("BrandId"));
			nextBC.setBrandCategoryPK(nextPK);
			nextBC.setName(rs.getNString("Name"));
			nextBC.setDescription(rs.getNString("Description"));
			nextBC.setStatus(rs.getBoolean("Status"));
		}
		
		return nextBC;
	}

	@Override
	protected List<BrandCategory> findAll(Connection con) throws SQLException {
		List<BrandCategory> list = new ArrayList<>();
		
		String sql = "SELECT * FROM [BrandCategory]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			BrandCategory nextBC = new BrandCategory();
			BrandCategoryPK nextPK = new BrandCategoryPK(rs.getInt("CateId"), rs.getInt("BrandId"));
			nextBC.setBrandCategoryPK(nextPK);
			nextBC.setName(rs.getNString("Name"));
			nextBC.setDescription(rs.getNString("Description"));
			nextBC.setStatus(rs.getBoolean("Status"));
			list.add(nextBC);
		}
		return list;
	}

	@Override
	protected List<BrandCategory> findRange(Connection con, int begin, int end) throws SQLException {
		List<BrandCategory> list = new ArrayList<>();
		
		String sql = "  SELECT TOP(?) * FROM [BrandCategory]" + 
					" EXCEPT SELECT TOP(?) * FROM [BrandCategory]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			BrandCategory nextBC = new BrandCategory();
			BrandCategoryPK nextPK = new BrandCategoryPK(rs.getInt("CateId"), rs.getInt("BrandId"));
			nextBC.setBrandCategoryPK(nextPK);
			nextBC.setName(rs.getNString("Name"));
			nextBC.setDescription(rs.getNString("Description"));
			nextBC.setStatus(rs.getBoolean("Status"));
			list.add(nextBC);
		}
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		int result = 0;
		
		String sql = "SELECT COUNT(*)" +
					" AS [Result]" +
					" FROM [BrandCategory]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;
	}

    
    
}
