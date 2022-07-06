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

import com.localbrand.entities.Customer;
import com.localbrand.entities.Order;
import com.localbrand.entities.Payment;

public class OrderFacade extends AbstractFacade<Order> {

	@Override
	protected void create(Connection con, Order t) throws SQLException {
		String sql = "  INSERT INTO [Order] ([OrderDate], [Total], [Tax], [PayId], [CustomerId], [Status])\r\n" + 
				"values	(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1,(t.getOrderDate().getYear() + "-" + t.getOrderDate().getMonth() + "-" + t.getOrderDate().getDate()
			+ " " + t.getOrderDate().getHours() + ":" + t.getOrderDate().getMinutes() + ":" + t.getOrderDate().getSeconds()));
		ptm.setDouble(2, t.getTotal());
		ptm.setDouble(3, t.getTax());
		ptm.setInt(4, t.getPayId().getId());
		ptm.setInt(5, t.getCustomerId().getId());
		ptm.setString(6, t.getStatus());
		ptm.executeUpdate();
		
	}

	@Override
	protected void edit(Connection con, Order t) throws SQLException {
		String sql = "UPDATE [Order] \r\n" + 
				"SET [OrderDate] = ?\r\n" + 
				", [Total] = ?\r\n" + 
				", [Tax] = ?\r\n" + 
				", [PayId] = ?\r\n" + 
				", [CustomerId] = ?\r\n" + 
				", [Status] = ?\r\n" + 
				"WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);

		ptm.setString(1, 
				(t.getOrderDate().getYear() + "-" + t.getOrderDate().getMonth() + "-" + t.getOrderDate().getDate()
				+ " " + t.getOrderDate().getHours() + ":" + t.getOrderDate().getMinutes() + ":" + t.getOrderDate().getSeconds()));
		ptm.setDouble(2, t.getTotal());
		ptm.setDouble(3, t.getTax());
		ptm.setInt(4, t.getPayId().getId());
		ptm.setInt(5, t.getCustomerId().getId());
		ptm.setString(6, t.getStatus());
		ptm.setInt(7, t.getId());
		
		
		ptm.executeUpdate();
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		String sql = "DELETE FROM [Order]"
				+ " WHERE [Id] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ptm.executeUpdate();
		
	}

	@Override
	protected Order find(Connection con, Object id) throws SQLException {
		Order order = null;
		
		String sql = "SELECT * FROM [Order]\r\n" + 
				"  WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			order = new Order();
			order.setId(rs.getInt("Id"));
			
			String dateTime = rs.getString("OrderDate");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				order.setOrderDate(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			order.setTotal(rs.getDouble("Total"));
			order.setTax(rs.getDouble("Tax"));
			
			//FK
			Payment pm = new Payment();
			pm.setId(rs.getInt("PayId"));
			order.setPayId(pm);
			
			//FK
			Customer cm = new Customer();
			cm.setId(rs.getInt("CustomerId"));
			order.setCustomerId(cm);
			
			order.setStatus(rs.getString("Status"));
			
		}
		
		return order;
	}

	@Override
	protected List<Order> findAll(Connection con) throws SQLException {
		List<Order> list = new ArrayList<>();
		String sql = "SELECT * FROM [Order]";
		
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while (rs.next()) {
			Order order = new Order();
			order.setId(rs.getInt("Id"));
			
			String dateTime = rs.getString("OrderDate");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				order.setOrderDate(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			order.setTotal(rs.getDouble("Total"));
			order.setTax(rs.getDouble("Tax"));
			
			//FK
			Payment pm = new Payment();
			pm.setId(rs.getInt("PayId"));
			order.setPayId(pm);
			
			//FK
			Customer cm = new Customer();
			cm.setId(rs.getInt("CustomerId"));
			order.setCustomerId(cm);
			order.setStatus(rs.getString("Status"));
			
			list.add(order);
		}
		
		return list;
	}

	@Override
	protected List<Order> findRange(Connection con, int begin, int end) throws SQLException {
		List<Order> list = new ArrayList<>();
		
		String sql = "  SELECT TOP(?) * FROM [Order] \r\n" + 
				"  EXCEPT SELECT TOP(?) * FROM [Order]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			Order order = new Order();
			order.setId(rs.getInt("Id"));
			
			String dateTime = rs.getString("OrderDate");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				order.setOrderDate(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			order.setTotal(rs.getDouble("Total"));
			order.setTax(rs.getDouble("Tax"));
			
			//FK
			Payment pm = new Payment();
			pm.setId(rs.getInt("PayId"));
			order.setPayId(pm);
			
			//FK
			Customer cm = new Customer();
			cm.setId(rs.getInt("CustomerId"));
			order.setCustomerId(cm);
			order.setStatus(rs.getString("Status"));
			
			list.add(order);
		}
		
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		int result = 0;
		
		String sql = "SELECT COUNT([Id])" +
					" AS [Result]" +
					" FROM [Order]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;	
	}

   
    
}
