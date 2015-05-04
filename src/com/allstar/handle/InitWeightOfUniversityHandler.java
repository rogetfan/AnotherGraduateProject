package com.allstar.handle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allstar.dao.DaoConfig;
import com.allstar.dao.DaoInstance;

public class InitWeightOfUniversityHandler
{
	private static DaoInstance di;
    public static void main(String args[]) throws IOException
    {
    	DaoConfig.init();
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
		try
		{
			di.CREATE_TABLE_WEIGHT_OF_UNIVERSITY();
		}
		catch (SQLException e1)
		{
			System.err.println("Create Table WEIGHT_OF_UNIVERSITY Failed");
			e1.printStackTrace();
		}
		handle("lqk09");
//		handle("lqk10");
//		handle("lqk11");
//		handle("lqk12");
//		handle("lqk13");
//		handle("lqk14");
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
    
    public static void handle(String TableName)
    {
    	String temp_school=null;
    	//文科成绩列表
    	List<Double> list_ba = new ArrayList<Double>();
    	//理科成绩列表
    	List<Double> list_sci = new ArrayList<Double>();
    	
    	try
		{
			for(String s:di.queryPointOrderByUniversity(TableName))
			{
				String[] ss= s.split(":");
				System.out.println(ss[0]);
				System.out.println(ss[1]);
				System.out.println(ss[2]);
				if(temp_school==null)
				{
					temp_school = ss[1];
					if(di.parseExameNumber(ss[0])==91)
					{
						list_ba.add(Double.parseDouble(ss[2]));
					}
					else if (di.parseExameNumber(ss[0])==95)
					{
						list_sci.add(Double.parseDouble(ss[2]));
					}
					else
					{
						continue;
					}
				} 
				else if(temp_school==ss[1])
				{
					if(di.parseExameNumber(ss[0])==91)
					{
						list_ba.add(Double.parseDouble(ss[2]));
					}
					else if (di.parseExameNumber(ss[0])==95)
					{
						list_sci.add(Double.parseDouble(ss[2]));
					}
					else
					{
						continue;
					}
				} 
				else 
				{
					if(!list_ba.isEmpty())
					{
					   //计算并插入数据库	
					}
				    if(!list_sci.isEmpty())
				    {
				       //计算并插入数据库
				    }
					temp_school=ss[1];
					if(di.parseExameNumber(ss[0])==91)
					{
						list_ba.add(Double.parseDouble(ss[2]));
					}
					else if (di.parseExameNumber(ss[0])==95)
					{
						list_sci.add(Double.parseDouble(ss[2]));
					}
					else
					{
						continue;
					}
				}
			}
		}
		catch (SQLException e1)
		{
			System.err.println("Query Data ERROR FROM　LQK*");
			e1.printStackTrace();
		}
    }
}
