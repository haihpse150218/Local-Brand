package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.BrandCategoryPK;

public class BrandCategoryFacade extends AbstractFacade<BrandCategory> {

	@Override
	protected void create(Connection con, BrandCategory t) throws SQLException {
		
		String sql = "INSERT INTO [BrandCategory]"
				+ "([CateId], [BrandId], [Name], [Description], [Status])"
				+ "VALUES (?, ?, ?, ?, 1)";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, t.getBrandCategoryPK().getCateId());
		ptm.setInt(2, t.getBrandCategoryPK().getBrandId());
		ptm.setNString(3, t.getName());
		ptm.setNString(4, t.getDescription());
	
		ptm.executeUpdate();
	}

	@Override
	protected void edit(Connection con, BrandCategory t) throws SQLException {
		
		String sql = "UPDATE [BrandCategory] "
				+ "SET [Name] = ?,"
				+ "[Description] = ? "
				+ "WHERE [CateId] = ? "
				+ "AND [BrandId] = ?"
				+ "AND [Status] = 1";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		ptm.setInt(3, t.getBrandCategoryPK().getCateId());
		ptm.setInt(4, t.getBrandCategoryPK().getBrandId());
	
		ptm.executeUpdate();
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		
		String sql = "UPDATE [BrandCategory] "
				+ "SET [Status] = 0 "
				+ "WHERE [CateId] = ? "
				+ "AND [BrandId] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, ((BrandCategoryPK) id).getCateId());
		ptm.setInt(2, ((BrandCategoryPK) id).getBrandId());
		
		ptm.executeUpdate();
	}

	@Override
	protected BrandCategory find(Connection con, Object id) throws SQLException {

		BrandCategory returnBrandCate = null;
		
		String sql = "SELECT * FROM [BrandCategory] "
					+ "WHERE [CateId] = ? "
					+ "AND [BrandId] = ? ";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, ((BrandCategoryPK) id).getCateId());
		ptm.setInt(2, ((BrandCategoryPK) id).getBrandId());
		
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			returnBrandCate = new BrandCategory();
			BrandCategoryPK returnBrandCatePK = new BrandCategoryPK(rs.getInt("CateId"), rs.getInt("BrandId"));
			returnBrandCate.setBrandCategoryPK(returnBrandCatePK);
			returnBrandCate.setName(rs.getNString("Name"));
			returnBrandCate.setDescription(rs.getNString("Description"));
		}
		
		return returnBrandCate;
	}

	@Override
	protected List<BrandCategory> findAll(Connection con) throws SQLException {
		
		List<BrandCategory> list = null;
		
		String sql = "SELECT * FROM [BrandCategory] "
				+ "WHERE [Status] = 1";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			BrandCategory nextBrandCate = new BrandCategory();
			BrandCategoryPK nextBrandCatePK = new BrandCategoryPK(rs.getInt("CateId"), rs.getInt("BrandId"));
			nextBrandCate.setBrandCategoryPK(nextBrandCatePK);
			nextBrandCate.setName(rs.getNString("Name"));
			nextBrandCate.setDescription(rs.getNString("Description"));
			
			list.add(nextBrandCate);
		}
		
		return list;
	}

	@Override
	protected List<BrandCategory> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

    
    
}
