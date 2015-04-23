package com.allstar.dao;

import java.io.IOException;
import java.sql.SQLException;

public class test
{
	public static void main(String args[]) throws IOException, SQLException
	{
		DaoConfig.init();
		DaoInstance di =new DaoInstance("gk");
		di.connect();
		for(String s:di.queryAllUniversity("lqk10")){
			System.out.println(s);
		}
		di.disconnect();
	}
}
