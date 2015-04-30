package com.allstar.handle;

import java.io.IOException;
import java.sql.SQLException;
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
    	
    	
    	try
		{
			for(String s:di.queryPointOrderByUniversity(TableName))
			{
				String[] ss= s.split(":");
				System.out.println(ss[0]);
				System.out.println(ss[1]);
				System.out.println(ss[2]);
			}
		}
		catch (SQLException e1)
		{
			System.err.println("Query Data ERROR FROM　LQK*");
			e1.printStackTrace();
		}
    }
}
