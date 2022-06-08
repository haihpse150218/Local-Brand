package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Payment;

public class PaymentFacade extends AbstractFacade<Payment> {

	@Override
	protected void create(Connection con, Payment t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO [dbo].[Payment]\r\n" + 
				"           ([PayMethod]\r\n" + 
				"           ,[Status])\r\n" + 
				"VALUES (?,?);";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1, t.getPayMethod());
		ptm.setString(2, t.getStatus());
		ptm.executeUpdate();
		
	}

	@Override
	protected void edit(Connection con, Payment t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE [dbo].[Payment]\r\n" + 
				"   SET [PayMethod] = ?\r\n" + 
				"      ,[Status] = ?\r\n" + 
				" WHERE [Id]=?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1, t.getPayMethod());
		ptm.setString(2, t.getStatus());
		ptm.setInt(3, t.getId());
		ptm.executeUpdate();
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		Payment t = new Payment();
		String sql = "UPDATE [dbo].[Payment]\r\n" + 
				"   SET [Status] = ?\r\n" + 
				" WHERE [Id]=?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1,"0");
		ptm.setInt(2, (int)id);
		ptm.executeUpdate();
	}

	@Override
	protected Payment find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		Payment p = null;
		String sql = "SELECT [Id],[PayMethod],[Status]\r\n" + 
				"FROM [dbo].[Payment]\r\n" + 
				"WHERE [Id] = ? ;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, (int)id);
		ResultSet rs = ptm.executeQuery();
		if (rs.next()) {
			p = new Payment();
			p.setId(rs.getInt("Id"));
			p.setPayMethod(rs.getString("PayMethod"));
			p.setStatus(rs.getString("Status"));
		}
		return p;
	}

	@Override
	protected List<Payment> findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		List<Payment> list = new ArrayList<>();
		Payment p= null;
		String sql = "SELECT [Id],[PayMethod],[Status]\r\n" + 
					"FROM [dbo].[Payment];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		while (rs.next()) {
			p = new Payment();
			p.setId(rs.getInt("Id"));
			p.setPayMethod(rs.getString("PayMethod"));
			p.setStatus(rs.getString("Status"));
			list.add(p);
		}
		return list;
	}

	@Override
	protected List<Payment> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		List<Payment> list = new ArrayList<>();
		Payment p= null;
		String sql = "SELECT TOP (?) [Id]\r\n" + 
				"      ,[PayMethod]\r\n" + 
				"      ,[Status]\r\n" + 
				"  FROM [localbrand].[dbo].[Payment]\r\n" + 
				"EXCEPT SELECT TOP (?) [Id]\r\n" + 
				"      ,[PayMethod]\r\n" + 
				"      ,[Status]\r\n" + 
				"  FROM [localbrand].[dbo].[Payment];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		while (rs.next()) {
			p = new Payment();
			p.setId(rs.getInt("Id"));
			p.setPayMethod(rs.getString("PayMethod"));
			p.setStatus(rs.getString("Status"));
			list.add(p);
		}
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "SELECT COUNT([PayMethod])\r\n" + 
				"FROM [dbo].[Payment]";
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		if (rs.next()) {
			result = rs.getInt("");
		}
		return result;
	}

   
    
}
