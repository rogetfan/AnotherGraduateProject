package com.allstar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	// 连接数据库
	public void connect() throws SQLException
	{
		conn = DriverManager.getConnection(DaoConfig.getMysqlUrl(), DaoConfig.getUserName(), DaoConfig.getPassWord());
		if (conn.isClosed())
			System.err.println("Fail to connect to Mysql");
		String queryString = new String(DaoConfig.USE_DATABASE);
		PreparedStatement ps = conn.prepareStatement(convertTable(queryString, DataBase));
		ps.execute();
		ps.close();
	}

	// University实例的数据库表达形式
	public void CREATE_TABLE_WEIGHT_OF_UNIVERSITY() throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement(DaoConfig.CREATE_TABLE_WEIGHT_OF_UNIVERSITY);
		ps.execute();
		ps.close();
	}

	// 创建数据表，分配学校代号
	public void CREATE_TABLE_ASSIGN_NUMBER_TO_UNIVERSITY() throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement(DaoConfig.CREATE_TABLE_ASSIGN_NUMBER_TO_UNIVERSITY);
		ps.execute();
		ps.close();

	}

	// 将问号编成数据表名
	private String convertTable(String string, String replace)
	{
		int end = string.indexOf("?");
		String temp1 = string.substring(0, end);
		String temp2 = string.substring(end + 1);
		StringBuilder sb = new StringBuilder();
		sb.append(temp1);
		sb.append(replace);
		sb.append(temp2);
		return sb.toString();
	}

	// 根据文理科返回该校录取成绩
	// clazz根据数值来判断为文科还是理科
	//91 是文科
	//95是理科
	public List<Double> queryPointByUniversity(String TableName, String University, int clazz) throws SQLException
	{
		List<Double> list = new LinkedList<Double>();
		String queryString = new String(DaoConfig.QUERY_ALL_INFO_BY_UNIVERSITY);
		queryString = convertTable(queryString, TableName);
		PreparedStatement ps = conn.prepareStatement(queryString);
		ps.setString(1, University);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			list.add(rs.getDouble("ZF"));
		}
		rs.close();
		ps.close();
		return list;

	}
    //选取左右高校名称
	public List<String> queryAllUniversity(String TableName) throws SQLException
	{
		String queryString = new String(DaoConfig.QUERY_ALL_UNVERSITY);
		queryString = convertTable(queryString, TableName);
		PreparedStatement ps = conn.prepareStatement(queryString);
		LinkedList<String> list = new LinkedList<String>();
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			String university = rs.getString("YXMC");
			if (!list.contains(university))
				list.add(university);
		}
		rs.close();
		ps.close();
		return list;
	}
    //考生号示例 ： 14-120-222-91-0023
	//91是文科
	//95是理科
	public int parseExameNumber(String code)
	{
       return Integer.parseInt(code.substring(8, 10));
	}

	public void assignNumberToUniversity(List<String> universities) throws SQLException
	{
		for (String s : universities)
		{
			PreparedStatement ps = conn.prepareStatement(DaoConfig.INSERT_INTO_ASSIGN_NUMBER_TO_UNIVERSITY);
			ps.setString(1, s);
			int index = ps.executeUpdate();
			if (index == 0)
				System.err.println("Insert Error");
			ps.close();
		}
	}

	public List<String> queryPointOrderByUniversity(String TableName) throws SQLException
	{
		String queryString = new String(DaoConfig.QUERY_ALL_INFO_ORDER_BY_UNIVERSITY);
		queryString = convertTable(queryString, TableName);
		PreparedStatement ps = conn.prepareStatement(queryString);
		List<String> result=new ArrayList<String>();
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			StringBuilder sb = new StringBuilder();
			sb.append(rs.getString("KSH"));
			sb.append(":");
			sb.append(rs.getString("YXMC"));
			sb.append(":");
			sb.append(rs.getString("ZF"));
			result.add(sb.toString());
		}
		return result;
	}
	
	public void disconnect() throws SQLException
	{
		conn.close();
	}
}
