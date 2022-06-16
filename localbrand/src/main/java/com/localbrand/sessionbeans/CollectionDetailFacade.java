package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Collection;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.entities.CollectionDetailPK;
import com.localbrand.entities.Product;

public class CollectionDetailFacade extends AbstractFacade<CollectionDetail> {

	@Override
	protected void create(Connection con, CollectionDetail t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO [dbo].[CollectionDetail]\r\n" + 
				"VALUES (?,?,?,?);";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, t.getCollection().getId());
		ptm.setInt(2, t.getProduct().getId());
		ptm.setString(3, t.getName());
		ptm.setString(4, t.getStatus());
		ptm.executeUpdate();

	}

	@Override
	protected void edit(Connection con, CollectionDetail t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE [dbo].[CollectionDetail]\r\n" + 
				"   SET [Name] = ?\r\n" + 
				"      ,[Status] = ?\r\n" + 
				" WHERE [CollectionId] = ? AND [ProductId] = ?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1, t.getName());
		ptm.setString(2, t.getStatus());
		ptm.setInt(3, t.getCollection().getId());
		ptm.setInt(4, t.getProduct().getId());
		ptm.executeUpdate();
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		
		CollectionDetailPK cdpk = (CollectionDetailPK) id;
		String sql = "DELETE FROM [dbo].[CollectionDetail]\r\n" + 
				"WHERE [CollectionId] = ? AND [ProductId] = ?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, cdpk.getCollectionId());
		ptm.setInt(2, cdpk.getProductId());
		ptm.executeUpdate();
	}

	@Override
	protected CollectionDetail find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		CollectionDetailPK other = (CollectionDetailPK) id;
		CollectionDetail cd = null;
		CollectionDetailPK cdpk = null;
		Collection c = null;
		Product p = null;
		String sql = "SELECT [CollectionId]\r\n" + 
				"      ,[ProductId]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Status]\r\n" + 
				"FROM [dbo].[CollectionDetail]\r\n" + 
				"WHERE [CollectionId] = ? AND [ProductId] = ?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, other.getCollectionId());
		ptm.setInt(2, other.getProductId());
		ResultSet rs = ptm.executeQuery();
		if (rs.next()) {
			c = new Collection();
			p = new Product();
			cd = new CollectionDetail();
			cdpk = new CollectionDetailPK();
			c.setId(rs.getInt("CollectionId"));
			cdpk.setCollectionId(rs.getInt("CollectionId"));
			cd.setCollection(c);
			p.setId(rs.getInt("ProductId"));
			cdpk.setProductId(rs.getInt("ProductId"));
			cd.setProduct(p);
			cd.setName(rs.getString("Name"));
			cd.setStatus(rs.getString("Status"));
			cd.setCollectionDetailPK(cdpk);
		}
		return cd;
	}

	@Override
	protected List<CollectionDetail> findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		List<CollectionDetail> list = new ArrayList<>();
		CollectionDetail cd = null;
		CollectionDetailPK cdpk = null;
		Collection c = null;
		Product p = null;
		String sql = "SELECT [CollectionId]\r\n" + 
				"      ,[ProductId]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Status]\r\n" + 
				"FROM [dbo].[CollectionDetail];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		while (rs.next()) {
			c = new Collection();
			p = new Product();
			cd = new CollectionDetail();
			cdpk = new CollectionDetailPK();
			c.setId(rs.getInt("CollectionId"));
			cdpk.setCollectionId(rs.getInt("CollectionId"));
			cd.setCollection(c);
			p.setId(rs.getInt("ProductId"));
			cdpk.setProductId(rs.getInt("ProductId"));
			cd.setProduct(p);
			cd.setName(rs.getString("Name"));
			cd.setStatus(rs.getString("Status"));
			cd.setCollectionDetailPK(cdpk);
			list.add(cd);
		}
		return list;
	}

	@Override
	protected List<CollectionDetail> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		List<CollectionDetail> list = new ArrayList<>();
		CollectionDetail cd = null;
		CollectionDetailPK cdpk = null;
		Collection c = null;
		Product p = null;
		String sql = "SELECT TOP (?) [CollectionId]\r\n" + 
				"      ,[ProductId]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Status]\r\n" + 
				"  FROM [localbrand].[dbo].[CollectionDetail]\r\n" + 
				"EXCEPT SELECT TOP (?) [CollectionId]\r\n" + 
				"      ,[ProductId]\r\n" + 
				"      ,[Name]\r\n" + 
				"      ,[Status]\r\n" + 
				"  FROM [localbrand].[dbo].[CollectionDetail];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		while (rs.next()) {
			c = new Collection();
			p = new Product();
			cd = new CollectionDetail();
			cdpk = new CollectionDetailPK();
			c.setId(rs.getInt("CollectionId"));
			cdpk.setCollectionId(rs.getInt("CollectionId"));
			cd.setCollection(c);
			p.setId(rs.getInt("ProductId"));
			cdpk.setProductId(rs.getInt("ProductId"));
			cd.setProduct(p);
			cd.setName(rs.getString("Name"));
			cd.setStatus(rs.getString("Status"));
			cd.setCollectionDetailPK(cdpk);
			list.add(cd);
		}
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "SELECT COUNT([CollectionId])\r\n" + 
				"AS [Result] \r\n" + 
				"FROM [dbo].[CollectionDetail];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		return result;
	}

   
    
}
