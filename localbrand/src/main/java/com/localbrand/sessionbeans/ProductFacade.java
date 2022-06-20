package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.BrandCategoryPK;
import com.localbrand.entities.Customer;
import com.localbrand.entities.Product;

public class ProductFacade extends AbstractFacade<Product> {

	@Override
	protected void create(Connection con, Product t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void edit(Connection con, Product t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected Product find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Product> findAll(Connection con) throws SQLException {
		String sql = "  select * from Product";
		List<Product> list = new ArrayList<>();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt("Id"));
			product.setName(rs.getNString("Name"));
			product.setDescription(rs.getNString("Description"));
			product.setStatus(rs.getString("Status"));
			product.setColor(rs.getString("Color"));
			product.setSize(rs.getString("Size"));
			product.setIsMaster(rs.getBoolean("IsMaster"));
			product.setImgMaster(rs.getString("ImgMaster"));
			product.setImgChild(rs.getString("ImgChild"));
			product.setPrice(rs.getDouble("Price"));
			product.setContainer(rs.getInt("Container"));
			product.setDiscount(rs.getDouble("Discount"));
			product.setParentId(new ProductFacade().find(rs.getInt("ParentId")));
			product.setBrandId(new BrandFacade().find(rs.getInt("BrandId")));
			product.setCateId(new CategoryFacade().find(rs.getInt("CateId")));
			product.setStars(rs.getDouble("Stars"));
			list.add(product);
		}
		return list;
	}

	@Override
	protected List<Product> findRange(Connection con, int begin, int end) throws SQLException {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT TOP(?) * FROM [Product]\r\n" + "EXCEPT SELECT TOP(?) * FROM [Product]";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin - 1);
		ResultSet rs = ptm.executeQuery();
		while (rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt("Id"));
			product.setName(rs.getNString("Name"));
			product.setDescription(rs.getNString("Description"));
			product.setStatus(rs.getString("Status"));
			product.setColor(rs.getString("Color"));
			product.setSize(rs.getString("Size"));
			product.setIsMaster(rs.getBoolean("IsMaster"));
			product.setImgMaster(rs.getString("ImgMaster"));
			product.setImgChild(rs.getString("ImgChild"));
			product.setPrice(rs.getDouble("Price"));
			product.setContainer(rs.getInt("Container"));
			product.setDiscount(rs.getDouble("Discount"));
			product.setParentId(new ProductFacade().find(rs.getInt("ParentId")));
			product.setBrandId(new BrandFacade().find(rs.getInt("BrandId")));
			product.setCateId(new CategoryFacade().find(rs.getInt("CateId")));
			product.setStars(rs.getDouble("Stars"));
			list.add(product);
		}
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		int result = 0;
		String sql = "SELECT COUNT([Id]) AS [Result] FROM [Product]";
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();

		if (rs.next()) {
			result = rs.getInt("Result");
		}

		return result;
	}

}
