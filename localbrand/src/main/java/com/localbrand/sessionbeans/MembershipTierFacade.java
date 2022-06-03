package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.MembershipTier;

public class MembershipTierFacade extends AbstractFacade<MembershipTier> {

	@Override
	protected void create(Connection con, MembershipTier t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void edit(Connection con, MembershipTier t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected MembershipTier find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<MembershipTier> findAll(Connection con) throws SQLException {
		List<MembershipTier> list = new ArrayList<>();
		String sql = "SELECT TOP (1000) [Id]\r\n" + 
				"      ,[Rank]\r\n" + 
				"      ,[Description]\r\n" + 
				"      ,[Discount]\r\n" + 
				"      ,[MinimumCoins]\r\n" + 
				"  FROM [localbrand].[dbo].[MembershipTier]";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			MembershipTier membershipTier = new MembershipTier();
			membershipTier.setId(rs.getInt("Id"));
			membershipTier.setRank(rs.getString("Rank"));
			membershipTier.setDescription(rs.getString("Description"));
			membershipTier.setDiscount(rs.getDouble("Discount"));
			membershipTier.setMinimumCoins(rs.getInt("MinimumCoins"));
			list.add(membershipTier);
		}
		return list;
	}

	@Override
	protected List<MembershipTier> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

    
    
}
