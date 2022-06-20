package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.OrderDetailPK;
import com.localbrand.entities.Product;

public class OrderDetailFacade extends AbstractFacade<OrderDetail> {

	@Override
	protected void create(Connection con, OrderDetail t) throws SQLException {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO [dbo].[OrderDetail]\r\n" + 
				"VALUES (?,?,?,?,?);";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, t.getOrder1().getId());
		ptm.setInt(2, t.getProduct().getId());
		ptm.setInt(3,t.getQuantity());
		ptm.setDouble(4, t.getDiscount());
		ptm.setDouble(5, t.getPrice());
		ptm.executeUpdate();
	}

	@Override
	protected void edit(Connection con, OrderDetail t) throws SQLException {
		// TODO Auto-generated method stub
		String sql ="UPDATE [dbo].[OrderDetail]\r\n" + 
				"   SET [Quantity] = ?\r\n" + 
				"      ,[Discount] = ?\r\n" + 
				"      ,[Price] = ?\r\n" + 
				" WHERE [OrderId] = ? AND [ProductId] = ?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1,t.getQuantity());
		ptm.setDouble(2, t.getDiscount());
		ptm.setDouble(3, t.getPrice());
		ptm.setInt(4, t.getOrder1().getId());
		ptm.setInt(5, t.getProduct().getId());
		ptm.executeUpdate();
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		OrderDetailPK opk = (OrderDetailPK) id;
		String sql ="DELETE FROM [dbo].[OrderDetail]\r\n" + 
				"    WHERE [OrderId] = ? AND [ProductId] = ?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, opk.getOrderId());
		ptm.setInt(2, opk.getProductId());
		ptm.executeUpdate();
	}

	@Override
	protected OrderDetail find(Connection con, Object id) throws SQLException {
		// TODO Auto-generated method stub
		OrderDetailPK oid = (OrderDetailPK) id;
		OrderDetailPK opk = null;
		OrderDetail od = null;
		Order o = null;
		Product p = null;
		String sql ="SELECT [OrderId]\r\n" + 
				"      ,[ProductId]\r\n" + 
				"      ,[Quantity]\r\n" + 
				"      ,[Discount]\r\n" + 
				"      ,[Price]\r\n" + 
				"FROM [dbo].[OrderDetail]\r\n" + 
				"WHERE [OrderId]= ? AND [ProductId] = ?;";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, oid.getOrderId());
		ptm.setInt(2, oid.getProductId());
		ResultSet rs = ptm.executeQuery();
		if (rs.next()) {
			o = new Order();
			p = new Product();
			opk = new OrderDetailPK();
			od = new OrderDetail();
			o.setId(rs.getInt("OrderId"));
			opk.setOrderId(rs.getInt("OrderId"));
			od.setOrder1(o);
			p.setId(rs.getInt("ProductId"));
			opk.setProductId(rs.getInt("ProductId"));
			od.setProduct(p);
			od.setQuantity(rs.getInt("Quantity"));
			od.setDiscount(rs.getDouble("Discount"));
			od.setPrice(rs.getDouble("Price"));
			od.setOrderDetailPK(opk);
		}
		return od;
	}

	@Override
	protected List<OrderDetail> findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		List<OrderDetail> list = new ArrayList<>();
		OrderDetailPK opk = null;
		OrderDetail od = null;
		Order o = null;
		Product p = null;
		String sql ="SELECT [OrderId]\r\n" + 
				"      ,[ProductId]\r\n" + 
				"      ,[Quantity]\r\n" + 
				"      ,[Discount]\r\n" + 
				"      ,[Price]\r\n" + 
				"FROM [dbo].[OrderDetail];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		while (rs.next()) {
			o = new Order();
			p = new Product();
			opk = new OrderDetailPK();
			od = new OrderDetail();
			o.setId(rs.getInt("OrderId"));
			opk.setOrderId(rs.getInt("OrderId"));
			od.setOrder1(o);
			p.setId(rs.getInt("ProductId"));
			opk.setProductId(rs.getInt("ProductId"));
			od.setProduct(p);
			od.setQuantity(rs.getInt("Quantity"));
			od.setDiscount(rs.getDouble("Discount"));
			od.setPrice(rs.getDouble("Price"));
			od.setOrderDetailPK(opk);
			list.add(od);
		}
		return list;
	}

	@Override
	protected List<OrderDetail> findRange(Connection con, int begin, int end) throws SQLException {
		// TODO Auto-generated method stub
		List<OrderDetail> list = new ArrayList<>();
		OrderDetailPK opk = null;
		OrderDetail od = null;
		Order o = null;
		Product p = null;
		String sql ="SELECT TOP (?) [OrderId]\r\n" + 
				"      ,[ProductId]\r\n" + 
				"      ,[Quantity]\r\n" + 
				"      ,[Discount]\r\n" + 
				"      ,[Price]\r\n" + 
				"  FROM [localbrand].[dbo].[OrderDetail]\r\n" + 
				"EXCEPT SELECT TOP (?) [OrderId]\r\n" + 
				"      ,[ProductId]\r\n" + 
				"      ,[Quantity]\r\n" + 
				"      ,[Discount]\r\n" + 
				"      ,[Price]\r\n" + 
				"  FROM [localbrand].[dbo].[OrderDetail];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		while (rs.next()) {
			o = new Order();
			p = new Product();
			opk = new OrderDetailPK();
			od = new OrderDetail();
			o.setId(rs.getInt("OrderId"));
			opk.setOrderId(rs.getInt("OrderId"));
			od.setOrder1(o);
			p.setId(rs.getInt("ProductId"));
			opk.setProductId(rs.getInt("ProductId"));
			od.setProduct(p);
			od.setQuantity(rs.getInt("Quantity"));
			od.setDiscount(rs.getDouble("Discount"));
			od.setPrice(rs.getDouble("Price"));
			od.setOrderDetailPK(opk);
			list.add(od);
		}
		return list;
	}

	@Override
	protected int count(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "SELECT COUNT([OrderId])\r\n" + 
				"AS [Result]\r\n" + 
				"FROM [dbo].[OrderDetail];";
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		return result;
	}

    
}
