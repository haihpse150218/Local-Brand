package com.localbrand.sessionbeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFacade<T> {
	 static String url = "jdbc:sqlserver://"

//	 		+ "13.250.14.150:1433;"
			+ "localhost:1433;"
	 		+ "databaseName=localbrand;"
//	 		+ "user=sa;password=Admin123";
	 		+ "user=sa;password=12345";


    public AbstractFacade() {
            try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

    }
    protected abstract void create(Connection con, T t) throws SQLException;

    public void create(T t) throws SQLException {
    	try {
            //Connecting to a database
            Connection con = DriverManager.getConnection(url);
            //Executing the stm
            create(con, t);
            //Closing the connection
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
            throw new SQLException(ex.getMessage());
        }
    }

    protected abstract void edit(Connection con, T t) throws SQLException;
    public void edit(T t) throws SQLException {
        try {
            //Connecting to a database
            Connection con = DriverManager.getConnection(url);
            //Executing the stm
            edit(con, t);
            //Closing the connection
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
            throw new SQLException(ex.getMessage());
        }
    }

    protected abstract void remove(Connection con, Object id) throws SQLException;
    public void remove(Object id) throws SQLException {
        try {
            //Connecting to a database
            Connection con = DriverManager.getConnection(url);
            //Executing the stm
            remove(con, id);
            //Closing the connection
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
            throw new SQLException(ex.getMessage());
        }
    }

    protected abstract T find(Connection con, Object id) throws SQLException;
    
    public T find(Object id) throws SQLException {
        T t = null;
        try {
            //Connecting to a database
            Connection con = DriverManager.getConnection(url);
            //Executing the stm
            t = find(con, id);// chua coe{adasdas}
            //Closing the connection
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
            throw new SQLException(ex.getMessage());
        }
        return t;
    }

    protected abstract List<T> findAll(Connection con) throws SQLException;
    public List<T> findAll() throws SQLException {
        List<T> list = new ArrayList<>();
        try {
            //Connecting to a database
            Connection con = DriverManager.getConnection(url);
            list = findAll(con);
            //Closing the connection
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
            throw new SQLException(ex.getMessage());
        }
        return list;
    }
    protected abstract List<T> findRange(Connection con, int begin, int end) throws SQLException;
    public List<T> findRange(int[] range) throws SQLException {
        List<T> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url);
            list = findRange(con, range[0], range[1]);
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return list;
    }

    protected abstract int count(Connection con) throws SQLException;

    public int count() {
        int c = 0;
        try {
            Connection con = DriverManager.getConnection(url);
            c = count(con);
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return c;
    }
    
}
