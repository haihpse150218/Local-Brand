package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Generated;

import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.BrandCategoryPK;
import com.localbrand.entities.Customer;
import com.localbrand.entities.Product;

public class ProductFacade extends AbstractFacade<Product> {

	@Override
	protected void create(Connection con, Product t) throws SQLException {
		String sql = "  INSERT INTO [Product] ([Name],[Description],[Status],[Color],[Size],[IsMaster],"
				+ "[ImgMaster],[ImgChild],[Price],[Container],[Discount],[ParentId],[BrandId],[CateId],[Stars],[CreateDate])\r\n" + 
				"values	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1, t.getName());
		ptm.setString(2, t.getDescription());
		ptm.setString(3, t.getStatus());
		ptm.setString(4, t.getColor());
		ptm.setString(5, t.getSize());
		ptm.setBoolean(6, t.getIsMaster());
		ptm.setString(7, t.getImgMaster());
		ptm.setString(8, t.getImgChild());
		ptm.setDouble(9, t.getPrice());
		ptm.setInt(10, t.getContainer());
		ptm.setDouble(11, t.getDiscount());
		ptm.setInt(12, t.getParentId().getId());
		ptm.setInt(13, t.getBrandId().getId());
		ptm.setInt(14, t.getCateId().getId());
		ptm.setDouble(15, t.getStars());
		
		ptm.setString(16, 
			(t.getCreateDate().getYear() + "-" + t.getCreateDate().getMonth() + "-" + t.getCreateDate().getDate()
			+ " " + t.getCreateDate().getHours() + ":" + t.getCreateDate().getMinutes() + ":" + t.getCreateDate().getSeconds()));
		
		ptm.executeUpdate();

	}

	@Override
	protected void edit(Connection con, Product t) throws SQLException {
		String sql = "UPDATE [Product] \r\n" + 
				"SET [Name] = ?\r\n" + 
				", [Description] = ?\r\n" + 
				", [Status] = ?\r\n" + 
				", [Color] = ?\r\n" + 
				", [Size] = ?\r\n" + 
				", [IsMaster] = ?\r\n" + 
				", [ImgMaster] = ?\r\n" + 
				", [ImgChild] = ?\r\n" + 
				", [Price] = ?\r\n" + 
				", [Container] = ?\r\n" + 
				", [Discount] = ?\r\n" + 
				", [ParentId] = ?\r\n" + 
				", [BrandId] = ?\r\n" + 
				", [CateId] = ?\r\n" + 
				", [Stars] = ?\r\n" + 
				", [CreateDate] = ?\r\n" + 
				"WHERE [Id] = ?";

		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1, t.getName());
		ptm.setString(2, t.getDescription());
		ptm.setString(3, t.getStatus());
		ptm.setString(4, t.getColor());
		ptm.setString(5, t.getSize());
		ptm.setBoolean(6, t.getIsMaster());
		ptm.setString(7, t.getImgMaster());
		ptm.setString(8, t.getImgChild());
		ptm.setDouble(9, t.getPrice());
		ptm.setInt(10, t.getContainer());
		ptm.setDouble(11, t.getDiscount());
		ptm.setInt(12, t.getParentId().getId());
		ptm.setInt(13, t.getBrandId().getId());
		ptm.setInt(14, t.getCateId().getId());
		ptm.setDouble(15, t.getStars());
		
		ptm.setString(16, 
			(t.getCreateDate().getYear() + "-" + t.getCreateDate().getMonth() + "-" + t.getCreateDate().getDate()
			+ " " + t.getCreateDate().getHours() + ":" + t.getCreateDate().getMinutes() + ":" + t.getCreateDate().getSeconds()));
		
		ptm.setInt(17, t.getId());
		
		ptm.executeUpdate();

	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		
		String sql = "DELETE FROM [Product]"
				+ " WHERE [Id] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ptm.executeUpdate();

	}

	@Override
	protected Product find(Connection con, Object id) throws SQLException {
		String sql = "  select * from Product";
		Product product = new Product();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
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

			String dateTime = rs.getString("CreateDate");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				product.setCreateDate(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		return product;
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

			String dateTime = rs.getString("CreateDate");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				product.setCreateDate(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
			
			String dateTime = rs.getString("CreateDate");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				product.setCreateDate(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
