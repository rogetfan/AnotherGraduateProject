package com.allstar.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.allstar.model.StatisResult;
import com.allstar.model.University;

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
	
	public List<StatisResult> query_all_result_from_university_weight(String uniname,int clazz)
	{
		List<StatisResult> list  =  new ArrayList<StatisResult>();
		try 
		{
			int uninumber = query_uninumber(uniname);
			PreparedStatement ps = conn.prepareStatement(DaoConfig.QUERY_RESULT_FROM_UNIVERSITY_WEIGHT);
		    ps.setInt(1, uninumber);
		    ps.setInt(2, clazz);
		    ResultSet rs = ps.executeQuery();
		    while(rs.next())
		    {
		    	StatisResult sr =new StatisResult();
		    	sr.setIndex(rs.getInt("index"));
		    	sr.setTotal(rs.getInt("total"));
		    	sr.setYear(rs.getInt("year"));
		    	list.add(sr);
		    }
		    rs.close();
		    ps.close();
		} 
		catch (SQLException e) 
		{
			System.err.println("Query UniNumber error");
			e.printStackTrace();
		}
		return list;
	}
	
	private int query_uninumber(String uniname) throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement(DaoConfig.QUERY_UNINUMBER);
		ps.setString(1, uniname);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt("UniNumber");
	}
	
	public int query_quantity_from_lqk(String tablename,int limit,String school) throws SQLException
	{
		String queryString =  new String(DaoConfig.QUERY_FROM_UNIVERSITY_WEIGHT);
		queryString=convertTable(queryString,tablename);
		queryString=add_limit(limit,queryString);
		PreparedStatement ps = conn.prepareStatement(queryString);
		ResultSet rs = ps.executeQuery();
		int count=0;
		while(rs.next())
		{
			if(rs.getString("YXMC").equals(school))
			count++;
		}
		rs.close();
		ps.close();
		return count;
	}

	private String add_limit(int limit,String string)
	{
		StringBuilder sb=new StringBuilder();
		sb.append(string);
		sb.append(" limit ");
		sb.append("0,");
		sb.append(limit);
		return sb.toString();
	}  
	
	
	
	public void drop_table(String tablename) throws SQLException
	{
		String queryString = new String(DaoConfig.DROP_TABLE);
		queryString=convertTable(queryString, tablename);
		PreparedStatement ps = conn.prepareStatement(queryString);
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
    //选取所有高校名称
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
    //为每个学校分配一个编号
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
	
	//插入每个学校的实例
	public void InsertUniversityInstance(University u) throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement(DaoConfig.INSERT_TABLE_WEIGHT_OF_UNIVERSITY);
		ps.setInt(1,u.getUniversity_number());
		ps.setDouble(2, u.getSigma());
		ps.setDouble(3, u.getMu());
		ps.setInt(4, u.getYear());
		ps.setInt(5, u.getTotal());
		ps.setInt(6, u.getIndex());
		ps.setInt(7, u.getClazz());
		ps.setInt(8, u.getRank());
		ps.executeUpdate();
		ps.close();
	}
	//通过学校名称检索编号
    public int querySchoolNumberByName(String name) throws SQLException
    {
    	PreparedStatement ps = conn.prepareStatement(DaoConfig.QUERY_NUMBER_TO_UNIVERSITY);
    	ps.setString(1, name);
    	ResultSet rs=ps.executeQuery();
    	rs.next();
    	int number=rs.getInt("UniNumber");
    	rs.close();
    	ps.close();
    	return number;
    	
    }
    //检索所有的学校编号
    public HashMap<String,Integer> queryAllSchoolNumberByName() throws SQLException
    {
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		PreparedStatement ps = conn.prepareStatement(DaoConfig.QUERY_ALL_NUMBER_TO_UNIVERSITY);
		ResultSet rs= ps.executeQuery();
		while(rs.next())
		{
			map.put(rs.getString("UniName"), rs.getInt("UniNumber"));
		}
		rs.close();
		ps.close();
    	return map;
    	
    }
    
    //检索该年所有人的分数
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
		rs.close();
		ps.close();
		return result;
	}
	
	public void disconnect() throws SQLException
	{
		conn.close();
	}
}
