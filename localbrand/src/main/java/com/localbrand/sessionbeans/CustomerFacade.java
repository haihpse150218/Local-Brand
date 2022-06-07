package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Customer;
import com.localbrand.entities.MembershipTier;

public class CustomerFacade extends AbstractFacade<Customer> {

	@Override
	protected void create(Connection con, Customer t) throws SQLException {
		String sql = "INSERT INTO dbo.Customer(Name, username, Password, Avatar, Email, Phone, Address, Status, Coins, RankId)\r\n" + 
				"  VALUES("
				+ "?, -- Name - nvarchar(50)\r\n" + 
				"  ?  , -- username - varchar(100)\r\n" + 
				"  ?  , -- Password - char(200)\r\n" + 
				"  ?  , -- Avatar - varchar(max)\r\n" + 
				"  ?  , -- Email - varchar(50)\r\n" + 
				"  ?  , -- Phone - varchar(50)\r\n" + 
				"  ? , -- Address - nvarchar(50)\r\n" + 
				"  ?  , -- Status - varchar(50)\r\n" + 
				"  ?   , -- Coins - int\r\n" + 
				"  ?   -- RankId - int \r\n" + 
				"  )";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setNString(1, t.getName());
		stm.setString(2, t.getUsername());
		stm.setString(3, t.getPassword());
		stm.setString(4, t.getAvatar());
		stm.setString(5, t.getEmail());
		stm.setString(6, t.getPhone());
		stm.setNString(7, t.getAddress());
		stm.setString(8, t.getStatus());
		stm.setInt(10, t.getCoins());
		stm.setInt(11, t.getRankId().getId());
		if (stm.executeUpdate() != 1) {
			try {
				throw new Exception();
            } catch (Exception ex) {
                System.out.println("err create: " + ex);
                throw new SQLException(ex.getMessage());
            }
		}
	}

	@Override
	protected void edit(Connection con, Customer t) throws SQLException {
		String sql ="UPDATE dbo.Customer \r\n" + 
				"SET Name = ?, Password = ?, Avatar =?, Email =?, Phone = ?, Address = ?, Status =?, Coins = ?, RankId =?\r\n" + 
				"WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, t.getName());
		stm.setString(2, t.getPassword());
		stm.setString(3, t.getAvatar());
		stm.setString(4, t.getEmail());
		stm.setString(5, t.getPhone());
		stm.setString(6, t.getAddress());
		stm.setString(7, t.getStatus());
		stm.setInt(8, t.getCoins());
		stm.setInt(9, t.getRankId().getId());
		if (stm.executeUpdate() != 1) {
            try {
            	throw new Exception();
            } catch (Exception ex) {
                System.out.println("err" + ex);
                throw new SQLException(ex.getMessage());
            }
        }

	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		String sql = "DELETE FROM dbo.Customer WHERE id = ?" ;
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, Integer.parseInt(id.toString()));
		if (stm.executeUpdate() != 1) {
            try {
            	throw new Exception();
            } catch (Exception ex) {
                System.out.println("err" + ex);
                throw new SQLException(ex.getMessage());
            }
        }

	}

	@Override
	protected Customer find(Connection con, Object id) throws SQLException {
		Customer customer = null;
		String sql = "SELECT * FROM dbo.Customer WHERE Id = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, Integer.parseInt(id.toString()));
		ResultSet rs = stm.executeQuery();
		if(rs.next()) {
			customer = new Customer();
			customer.setId(rs.getInt("Id"));
			customer.setName(rs.getNString("Name"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("Password"));
			customer.setAddress(rs.getString("Avatar"));
			customer.setEmail(rs.getString("Email"));
			customer.setPhone(rs.getString("Phone"));
			customer.setAddress(rs.getNString("Status"));
			customer.setCoins(rs.getInt("Coins"));
			customer.setRankId(new MembershipTierFacade().find(rs.getInt("RankId")));// return MembershipTier and ref to Rank ID
		}
		return customer;
	}

	@Override
	protected List<Customer> findAll(Connection con) throws SQLException {
		List<Customer> list = new ArrayList<>();
		String sql = "SELECT * FROM dbo.Customer";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()) {
			Customer customer = new Customer();
			customer = new Customer();
			customer.setId(rs.getInt("Id"));
			customer.setName(rs.getNString("Name"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("Password"));
			customer.setAddress(rs.getString("Avatar"));
			customer.setEmail(rs.getString("Email"));
			customer.setPhone(rs.getString("Phone"));
			customer.setAddress(rs.getString("Status"));
			customer.setCoins(rs.getInt("Coins"));
			customer.setRankId(new MembershipTierFacade().find(rs.getInt("RankId")));// return MembershipTier and ref to Rank ID
			list.add(customer);
		}
		
		return list;
	}

	@Override
	protected List<Customer> findRange(Connection con, int begin, int end) throws SQLException {
		if(begin <  1) {
			begin = 1;
		}
		begin = begin - 1; // begin start from 1
		List<Customer> list = new ArrayList<>();
        String sql = "SELECT TOP (?) * \n"
                + " FROM dbo.Customer\n"
                + "EXCEPT SELECT TOP (?) * \n"
                + "FROM dbo.Customer ";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, end);
        stm.setInt(2, begin);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
        	Customer customer = new Customer();
			customer = new Customer();
			customer.setId(rs.getInt("Id"));
			customer.setName(rs.getNString("Name"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("Password"));
			customer.setAddress(rs.getString("Avatar"));
			customer.setEmail(rs.getString("Email"));
			customer.setPhone(rs.getString("Phone"));
			customer.setAddress(rs.getString("Status"));
			customer.setCoins(rs.getInt("Coins"));
			customer.setRankId(new MembershipTierFacade().find(rs.getInt("RankId")));// return MembershipTier and ref to Rank ID
			list.add(customer);
            
        }
        return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		return 0;
	}

}
