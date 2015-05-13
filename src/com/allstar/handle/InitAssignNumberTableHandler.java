package com.allstar.handle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.allstar.model.dao.DaoConfig;
import com.allstar.model.dao.DaoInstance;

//先从五年中选取所有高校
//再将其插入数据库表中
//然后再将学校录取情况，分年份，分文理的保存在另一张表中
//其中需要用到快速排序算法来计算该校文科(或者理科)在该年的排名情况
//这样计算就简单了。

//录取分数表，lqk09,lqk10,lqk11,lqk12,lqk13,lqk14

public class InitAssignNumberTableHandler {
	
	private static DaoInstance di;
	public static void work()
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
		try 
		{
			di.drop_table("uni_name_number");
		} 
		catch (SQLException e2) 
		{
			System.err.println("Drop table error");
			e2.printStackTrace();
		}		
		try
		{
			di.CREATE_TABLE_ASSIGN_NUMBER_TO_UNIVERSITY();
		}
		catch (SQLException e1)
		{
			System.err.println("Create Table UNI_NAME_NUMBER Failed");
			e1.printStackTrace();
		}
		try
		{
			long begin = System.currentTimeMillis();
			List<String> lqk=new ArrayList<String>();
			List<String> lqk_temp =new ArrayList<String>();
			lqk_temp.addAll(di.queryAllUniversity("lqk09"));
			lqk_temp.addAll(di.queryAllUniversity("lqk10"));
			lqk_temp.addAll(di.queryAllUniversity("lqk11"));
			lqk_temp.addAll(di.queryAllUniversity("lqk12"));
			lqk_temp.addAll(di.queryAllUniversity("lqk13"));
			lqk_temp.addAll(di.queryAllUniversity("lqk14"));
			for(String s:lqk_temp )
			{
			  if(!lqk.contains(s))
				  lqk.add(s);
			}
			di.assignNumberToUniversity(lqk);
			long usage = System.currentTimeMillis()-begin;
			System.out.println("Used:"+usage/1000+" seconds");
			System.out.println("Finish to initlize table");
		}
		catch (SQLException e)
		{
			System.err.println();
			e.printStackTrace();
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
	
	public static void main(String args[])
	{
		work();
	}
}
