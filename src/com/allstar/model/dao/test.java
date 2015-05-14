package com.allstar.model.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.allstar.model.StatisResult;

public class test  
{
	private static DaoInstance di;
	public static void main(String args[])
	{
		try 
		{
			DaoConfig.init();
		}
		catch (IOException e3) 
		{
			System.err.println("Initialize database configuration error");
			e3.printStackTrace();
		}
	    di =new DaoInstance("gk");
		try
		{
			di.connect();
		}
		catch (SQLException e)
		{
			System.err.println("Connect to DataBase Error");
			e.printStackTrace();
		}
		
		List<StatisResult> list = di.query_all_result_from_university_weight("北京大学", 95);
		for(StatisResult sr:list)
		{
			System.out.println(sr.getYear());
		}
		
		try 
		{
			System.out.println(di.query_quantity_from_lqk("lqk14", 20, "北京大学"));
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try
		{
			di.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println("DataBase Closed ERROR");
			e.printStackTrace();
		}
	}
	
}
