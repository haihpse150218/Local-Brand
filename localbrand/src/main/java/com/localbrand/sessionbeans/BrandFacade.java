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

import com.localbrand.entities.Brand;
import com.localbrand.entities.Store;

public class BrandFacade extends AbstractFacade<Brand> {

    private void checkNull(Brand t) {
		if (t.getStatus() == null) {
			t.setStatus("Active");
		}
		
	}
	
	@Override
	protected void create(Connection con, Brand t) throws SQLException {
		String sql = "  INSERT INTO [Brand] ([Name],[Description],[Status],[CreateDate],[Logo],[Banner],[Stars])\r\n" + 
				"values	(?, ?, ?, ?, ?, ?,?)";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		ptm.setString(3, t.getStatus());
		ptm.setString(4, 
				(t.getCreateDate().getYear() + "-" + t.getCreateDate().getMonth() + "-" + t.getCreateDate().getDate()
				+ " " + t.getCreateDate().getHours() + ":" + t.getCreateDate().getMinutes() + ":" + t.getCreateDate().getSeconds()));
		ptm.setString(5, t.getLogo());	
		ptm.setString(6, t.getBanner());
		ptm.setDouble(7, t.getStars());
		
		ptm.executeUpdate();
		
	}

	@Override
	protected void edit(Connection con, Brand t) throws SQLException {
		String sql = "UPDATE [Brand] \r\n" + 
				"SET [Name] = ?\r\n" + 
				", [Description] = ?\r\n" + 
				", [Status] = ?\r\n" + 
				", [CreateDate] = ?\r\n" + 
				", [Logo] = ?\r\n" + 
				", [Banner] = ?\r\n" + 
				", [Stars] = ?\r\n" + 
				"WHERE [Id] = ?";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setNString(1, t.getName());
		ptm.setNString(2, t.getDescription());
		ptm.setString(3, t.getStatus());
		java.sql.Date sqlCreateTime = new java.sql.Date(t.getCreateDate().getTime());
		ptm.setDate(4, sqlCreateTime);
		ptm.setString(5, t.getLogo());	
		ptm.setString(6, t.getBanner());
		ptm.setDouble(7, t.getStars());
		ptm.setInt(8, t.getId());
		ptm.executeUpdate();
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		
		String sql = "DELETE FROM [Brand]"
				+ " WHERE [Id] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ptm.executeUpdate();
		
	}

	@Override
	protected Brand find(Connection con, Object id) throws SQLException {
		Brand brand = null;
		
		String sql = "SELECT * FROM [Brand]\r\n" + 
				"  WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			brand = new Brand();
			brand.setId(rs.getInt("Id"));
			brand.setName(rs.getNString("Name"));
			brand.setDescription(rs.getNString("Description"));
			brand.setStatus(rs.getString("Status"));
			brand.setStars(rs.getDouble("Stars"));
			String dateTime = rs.getString("CreateDate");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				brand.setCreateDate(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			brand.setLogo(rs.getString("Logo"));
			brand.setBanner(rs.getString("Banner"));
			
		}
		
		return brand;
		
	}

	@Override
	protected List<Brand> findAll(Connection con) throws SQLException {
		List<Brand> list = new ArrayList<>();
		String sql = "SELECT * FROM [Brand]";
		
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while (rs.next()) {
			Brand brand = new Brand();
			brand = new Brand();
			brand.setId(rs.getInt("Id"));
			brand.setName(rs.getNString("Name"));
			brand.setDescription(rs.getNString("Description"));
			brand.setStatus(rs.getString("Status"));
			brand.setStars(rs.getDouble("Stars"));
			String dateTime = rs.getString("CreateDate");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				brand.setCreateDate(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			brand.setLogo(rs.getString("Logo"));
			brand.setBanner(rs.getString("Banner"));
			
			list.add(brand);
		}
		
		return list;
	
	}

	@Override
	protected List<Brand> findRange(Connection con, int begin, int end) throws SQLException {
		List<Brand> list = new ArrayList<>();
		
		String sql = "  SELECT TOP(?) * FROM [Brand] \r\n" + 
				"  EXCEPT SELECT TOP(?) * FROM [Brand]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			Brand brand = new Brand();
			brand = new Brand();
			brand.setId(rs.getInt("Id"));
			brand.setName(rs.getNString("Name"));
			brand.setDescription(rs.getNString("Description"));
			brand.setStatus(rs.getString("Status"));
			brand.setStars(rs.getDouble("Stars"));
			String dateTime = rs.getString("CreateDate");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				brand.setCreateDate(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			brand.setLogo(rs.getString("Logo"));
			brand.setBanner(rs.getString("Banner"));
			
			list.add(brand);
		}
		
		return list;
	
	}

	@Override
	protected int count(Connection con) throws SQLException {
		int result = 0;
		
		String sql = "SELECT COUNT([Id])" +
					" AS [Result]" +
					" FROM [Brand]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;	
		
	}

    
}
