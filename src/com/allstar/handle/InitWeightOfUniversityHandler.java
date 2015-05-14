package com.allstar.handle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.allstar.model.University;
import com.allstar.model.dao.DaoConfig;
import com.allstar.model.dao.DaoInstance;
import com.allstar.statistics.MaximumLikelihood;
import com.allstar.statistics.UniversityQuickSort;

public class InitWeightOfUniversityHandler
{
	private static DaoInstance di;
	private static HashMap<String, Integer> map;

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
		di = new DaoInstance("gk");
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
			di.drop_table("weight_of_university");
		} 
		catch (SQLException e2) 
		{
			System.err.println("Drop table weight_of_university error");
			e2.printStackTrace();
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
		try
		{
			map = di.queryAllSchoolNumberByName();
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		handle("lqk09");
		handle("lqk10");
		handle("lqk11");
		handle("lqk12");
		handle("lqk13");
		handle("lqk14");
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
		String temp_school = null;
		// 文科成绩列表
		List<Double> list_ba = new ArrayList<Double>();
		// 理科成绩列表
		List<Double> list_sci = new ArrayList<Double>();
		ArrayList<University> list_uni_ba = new ArrayList<University>();
		ArrayList<University> list_uni_sci = new ArrayList<University>();
		try
		{
			for (String s : di.queryPointOrderByUniversity(TableName))
			{
				String[] ss = s.split(":");
//				System.out.println(s);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				if (temp_school == null)
				{
					temp_school = ss[1];
				}
				else
					if (temp_school.equals(ss[1]) )
					{
						
					}
					else
					{
						if (!list_ba.isEmpty())
						{   //System.out.println("Now school is "+temp_school);
							MaximumLikelihood ml = new MaximumLikelihood(list_ba);
							ml.analyse();
							University u = new University();
							u.setClazz(91);
							// u.setIndex(index);
							// u.setRank(rank);
							u.setMu(ml.getMu());
							u.setSigma(ml.getSigma());
							u.setTotal(list_ba.size());
							u.setUniversity_name(temp_school);
							u.setUniversity_number(map.get(temp_school));
							u.setYear(getYearByTablename(TableName));
							list_uni_ba.add(u);
							//System.out.println(u);
							list_ba.clear();
						}
						if (!list_sci.isEmpty())
						{
							// 计算并存入缓存列表
							MaximumLikelihood ml = new MaximumLikelihood(list_sci);
							ml.analyse();
							University u = new University();
							u.setClazz(95);
							// u.setIndex(index);
							// u.setRank(rank);
							u.setMu(ml.getMu());
							u.setSigma(ml.getSigma());
							u.setTotal(list_sci.size());
							u.setUniversity_name(temp_school);
							u.setUniversity_number(map.get(temp_school));
							u.setYear(getYearByTablename(TableName));
							list_uni_sci.add(u);
							list_sci.clear();
						}
						temp_school = ss[1];
				}
				if (di.parseExameNumber(ss[0]) == 91)
				{
					list_ba.add(Double.parseDouble(ss[2]));
				}
				else
					if (di.parseExameNumber(ss[0]) == 95)
					{
						list_sci.add(Double.parseDouble(ss[2]));
					}
					else
					{
						
					}
			}
		}
		catch (SQLException e1)
		{
			System.err.println("Query Data ERROR FROM　LQK*");
			e1.printStackTrace();
		}
		System.out.println("Start to sort and insert");
		//排序，插入数据库
		insert(list_uni_ba);
		insert(list_uni_sci);
		System.out.println("End Insert");
		
	}

	private static void insert(ArrayList<University> list)
	{
		int total_index=0;
		UniversityQuickSort.quick_sort(list, 0, list.size()-1);
		for(int i=list.size()-1;i>=0;i--)
		{
		    University u=list.get(i);
		    u.setIndex(total_index);
		    total_index=total_index+u.getTotal();
		    u.setRank(list.size()-i);
		    try {
				di.InsertUniversityInstance(u);
			} catch (SQLException e) {
				System.err.println("Insert University Instance Error");
				e.printStackTrace();
			}
		}
	}
	
	private static int getYearByTablename(String tablename)
	{
		if (tablename.endsWith("09"))
			return 2009;
		else
			if (tablename.endsWith("10"))
				return 2010;
			else
				if (tablename.endsWith("11"))
					return 2011;
				else
					if (tablename.endsWith("12"))
						return 2012;
					else
						if (tablename.endsWith("13"))
							return 2013;
						else
							if (tablename.endsWith("14"))
								return 2014;
							else
								return -1;
	}
	
	public static void main(String args[])
	{
		work();
	}
}
