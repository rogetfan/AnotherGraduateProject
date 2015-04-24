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
		di.disconnect();
	}
}
