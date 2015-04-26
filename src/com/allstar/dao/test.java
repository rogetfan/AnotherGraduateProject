package com.allstar.dao;

import java.io.IOException;
import java.sql.SQLException;
import com.allstar.statistics.MaximumLikelihood;

public class test
{
	public static void main(String args[]) throws IOException, SQLException
	{
		DaoConfig.init();
		DaoInstance di =new DaoInstance("gk");
		di.connect();
		MaximumLikelihood ml =new MaximumLikelihood(di.queryPointByUniversity("lqk11", "北京大学"));
		ml.analyse();
		System.out.println(ml.getMu());
		System.out.println(ml.getSigma());
//		di.CREATE_TABLE_ASSIGN_NUMBER_TO_UNIVERSITY();
//		di.CREATE_TABLE_WEIGHT_OF_UNIVERSITY();
		String [] s=new String[3];
		s[0]="北京大学";
		s[1]="北京大学";
		s[2]="人民大学";
		di.assignNumberToUniversity(s);
		di.disconnect();
	}
}
