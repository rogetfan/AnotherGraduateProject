package com.allstar.model.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import com.allstar.statistics.MaximumLikelihood;

public class test
{
	public static void main(String args[]) throws IOException, SQLException
	{
		DaoConfig.init();
		DaoInstance di =new DaoInstance("gk");
		di.connect();
//		System.out.println(di.parseExameNumber("12120107918027"));
//		for(String s:di.queryPointOrderByUniversity("lqk10"))
//		{
//			System.out.println(s);
//		}
//		
//		MaximumLikelihood ml =new MaximumLikelihood(di.queryPointByUniversity("lqk11", "北京大学",11));
//		ml.analyse();
//		System.out.println(ml.getMu());
//		System.out.println(ml.getSigma());
//		di.CREATE_TABLE_ASSIGN_NUMBER_TO_UNIVERSITY();
		di.CREATE_TABLE_WEIGHT_OF_UNIVERSITY();
//		List<String> s=new ArrayList<String>();
//		s.add("北京大学");
//		s.add("北京大学");
//		s.add("人民大学");
//		System.out.println(s.size());
//		
//		di.assignNumberToUniversity(s);
//		System.out.println(di.querySchoolNumberByName("北京大学"));
//		for(Entry<String,Integer> e:di.queryAllSchoolNumberByName().entrySet())
//		{
//			System.out.println(e.getKey()+":"+e.getValue());
//		}
		di.disconnect();
	}
}
