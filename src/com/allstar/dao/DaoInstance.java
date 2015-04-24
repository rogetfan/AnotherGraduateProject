package com.allstar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoInstance
{
	private String DataBase;
    private Connection conn;
	public DaoInstance(String DataBase)
	{
		this.DataBase = DataBase;
	}

	public void connect() throws SQLException
	{
      conn=DriverManager.getConnection(DaoConfig.getMysqlUrl(), DaoConfig.getUserName(), DaoConfig.getPassWord());
      if(conn.isClosed())
			System.err.println("Fail to connect to Mysql");
      String queryString =new String(DaoConfig.USE_DATABASE);
      PreparedStatement ps=conn.prepareStatement(convertTable(queryString,DataBase));
      ps.execute();
      ps.close();
	}
    
	private String convertTable(String string,String replace){
		int end=string.indexOf("?");
		String temp1 = string.substring(0, end);
		String temp2 = string.substring(end+1);
		StringBuilder sb =new StringBuilder();
		sb.append(temp1);
		sb.append(replace);
		sb.append(temp2);
		return sb.toString();	    
	}
	
	public List<Double> queryPointByUniversity(String TableName,String University) throws SQLException
	{
		List<Double> list=new LinkedList<Double>();
		String queryString =new String(DaoConfig.QUERY_POINT_BY_UNIVERSITY);
		queryString =convertTable(queryString,TableName);
		PreparedStatement ps=conn.prepareStatement(queryString);
		ps.setString(1, University);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{		
		   list.add(rs.getDouble("ZF"));
		}
		rs.close();
		ps.close();
		return list;
		
	}
	
	public List<String> queryAllUniversity(String TableName) throws SQLException
	{
		String queryString =new String(DaoConfig.QUERY_ALL_UNVERSITY);
		queryString=convertTable(queryString,TableName);
		PreparedStatement ps=conn.prepareStatement(queryString);
		LinkedList<String> list=new LinkedList<String>();
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			String university =rs.getString("YXMC");
			if(!list.contains(university))
			    list.add(university);
		}
		rs.close();
		ps.close();
		return list;
	}
	
	public void assignNumberToUniversity(String [] universities)
	{
		
	}
	
	public void disconnect() throws SQLException
	{
          conn.close();
	}
}
