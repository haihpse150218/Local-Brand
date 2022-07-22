package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Feedback;
import com.localbrand.entities.Order;
import com.localbrand.entities.Product;

public class FeedbackFacade extends AbstractFacade<Feedback> {

	private void checkNull(Feedback t) {
		if (t.getStatus() == null) {
			t.setStatus(1);
		}
		
	}
	
	@Override
	protected void create(Connection con, Feedback t) throws SQLException {
		String sql = "  INSERT INTO [Feedback] ([FeedbackTime],[TextComment],[Voting],[Status],[ProductId],[OrderId])\r\n" + 
				"values	(?, ?, ?, ?, ?, ?)";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);
		//ptm.setString(1, 
		//	(t.getFeedbackTime().getYear() + "-" + t.getFeedbackTime().getMonth() + "-" + t.getFeedbackTime().getDate()
		//	+ " " + t.getFeedbackTime().getHours() + ":" + t.getFeedbackTime().getMinutes() + ":" + t.getFeedbackTime().getSeconds()));
		java.sql.Date sqlFeedbackTime = new java.sql.Date(t.getFeedbackTime().getTime());
		ptm.setDate(1, sqlFeedbackTime);
		ptm.setNString(2, t.getTextComment());
		ptm.setDouble(3, t.getVoting());
		ptm.setInt(4, t.getStatus());
		ptm.setInt(5, t.getProductId().getId());
		ptm.setInt(6, t.getOrderId().getId());
		
		ptm.executeUpdate();
		
	}

	@Override
	protected void edit(Connection con, Feedback t) throws SQLException {
		String sql = "UPDATE [Feedback] \r\n" + 
				"SET [FeedbackTime] = ?\r\n" + 
				", [TextComment] = ?\r\n" + 
				", [Voting] = ?\r\n" + 
				", [Status] = ?\r\n" + 
				", [ProductId] = ?\r\n" + 
				", [OrderId] = ?\r\n" + 
				"WHERE [Id] = ?";
		
		checkNull(t);
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setString(1, 
				(t.getFeedbackTime().getYear() + "-" + t.getFeedbackTime().getMonth() + "-" + t.getFeedbackTime().getDate()
				+ " " + t.getFeedbackTime().getHours() + ":" + t.getFeedbackTime().getMinutes() + ":" + t.getFeedbackTime().getSeconds()));
		ptm.setNString(2, t.getTextComment());
		ptm.setDouble(3, t.getVoting());
		ptm.setInt(4, t.getStatus());
		ptm.setInt(5, t.getProductId().getId());
		ptm.setInt(6, t.getOrderId().getId());
		ptm.setInt(7, t.getId());
		
		ptm.executeUpdate();
		
	}

	@Override
	protected void remove(Connection con, Object id) throws SQLException {
		String sql = "DELETE FROM [Feedback]"
				+ " WHERE [Id] = ?";
	
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ptm.executeUpdate();
		
	}

	@Override
	protected Feedback find(Connection con, Object id) throws SQLException {
		Feedback fb = null;
		
		String sql = "SELECT * FROM [Feedback]\r\n" + 
				"  WHERE [Id] = ?";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, Integer.parseInt(id.toString()));
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			fb = new Feedback();
			fb.setId(rs.getInt("Id"));
			
			String dateTime = rs.getString("FeedbackTime");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				fb.setFeedbackTime(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			fb.setTextComment(rs.getNString("TextComment"));
			fb.setVoting(rs.getDouble("Voting"));
			fb.setStatus(rs.getInt("Status"));
			
			//FK productID
			Product product = new Product();
			product.setId(rs.getInt("ProductId"));
			fb.setProductId(product);
			
			//FK orderID
			Order order = new Order();
			order.setId(rs.getInt("OrderId"));
			fb.setOrderId(order);
			
		}
		
		return fb;
		
	}

	@Override
	protected List<Feedback> findAll(Connection con) throws SQLException {
		List<Feedback> list = new ArrayList<>();
		String sql = "SELECT * FROM [Feedback]";
		
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while (rs.next()) {
			Feedback fb = new Feedback();
			fb.setId(rs.getInt("Id"));
			
			String dateTime = rs.getString("FeedbackTime");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				fb.setFeedbackTime(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			fb.setTextComment(rs.getNString("TextComment"));
			fb.setVoting(rs.getDouble("Voting"));
			fb.setStatus(rs.getInt("Status"));
			
			//FK productID
			Product product = new Product();
			product.setId(rs.getInt("ProductId"));
			fb.setProductId(product);
			
			//FK orderID
			Order order = new Order();
			order.setId(rs.getInt("OrderId"));
			fb.setOrderId(order);
			
			list.add(fb);
		}
		
		return list;
		
	}

	@Override
	protected List<Feedback> findRange(Connection con, int begin, int end) throws SQLException {
		List<Feedback> list = new ArrayList<>();
		
		String sql = "  SELECT TOP(?) * FROM [Feedback] \r\n" + 
				"  EXCEPT SELECT TOP(?) * FROM [Feedback]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ptm.setInt(1, end);
		ptm.setInt(2, begin-1);
		ResultSet rs = ptm.executeQuery();
		
		while (rs.next()) {
			Feedback fb = new Feedback();
			fb.setId(rs.getInt("Id"));
			
			String dateTime = rs.getString("FeedbackTime");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				fb.setFeedbackTime(fm.parse(dateTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			fb.setTextComment(rs.getNString("TextComment"));
			fb.setVoting(rs.getDouble("Voting"));
			fb.setStatus(rs.getInt("Status"));
			
			//FK productID
			Product product = new Product();
			product.setId(rs.getInt("ProductId"));
			fb.setProductId(product);
			
			//FK orderID
			Order order = new Order();
			order.setId(rs.getInt("OrderId"));
			fb.setOrderId(order);
			
			list.add(fb);
		}
		
		return list;
		
	}

	@Override
	protected int count(Connection con) throws SQLException {
		int result = 0;
		
		String sql = "SELECT COUNT([Id])" +
					" AS [Result]" +
					" FROM [Feedback]";
		
		PreparedStatement ptm = con.prepareStatement(sql);
		ResultSet rs = ptm.executeQuery();
		
		if (rs.next()) {
			result = rs.getInt("Result");
		}
		
		return result;	
	}

    
    
}
