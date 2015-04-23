package com.allstar.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 考生号  
 * 姓名
 * 院校代码
 * 院校名称
 * 专业代码
 * 专业名称
 * jhsxzdm
 * jhxzdm
 * ccdm
 * 学制
 * 录取批次代码
 * 录取专业序号
 * LQRQ
 * lqlxdm
 * 总分
 * ZGDM
 * **/

public class DaoConfig
{
	private static String UserName;
	private static String PassWord;
	private static String MysqlUrl;
    public final static String USE_DATABASE="use ?";
    public final static String QUERY_POINT_BY_UNIVERSITY="select * from ? where YXMC = ?";
    public final static String CREATE_TABLE_ASSIGN_NUMBER_TO_UNIVERSITY="CREATE TABLE `uni_name&number` (`UniNumber` INT(11) NOT NULL AUTO_INCREMENT,`UniName` VARCHAR(30) DEFAULT NULL,PRIMARY KEY (`UniNumber`)) ENGINE=INNODB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC";
	public final static String CREATE_TABLE_WEIGHT_OF_UNIVERSITY="CREATE TABLE `weight_of_university` (`UniNumber` int(11) NOT NULL,`sgima` float NOT NULL,`mu` float NOT NULL,`year` varchar(4) NOT NULL,PRIMARY KEY (`UniNumber`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC";
    public final static String INSERT_INTO_ASSIGN_NUMBER_TO_UNIVERSITY="INSERT INTO `gk`.`uni_name&number`(`UniName`) VALUES (?)";
    public final static String INSERT_TABLE_WEIGHT_OF_UNIVERSITY="INSERT INTO `gk`.`weight_of_university`(`UniNumber`,`sgima`,`mu`,`year`) VALUES ( ?,?,?,?);";
	public final static String QUERY_ALL_UNVERSITY="select YXMC from ?";
	public static void init() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("setting.properties");
		prop.load(fis);
		UserName=prop.getProperty("user_name", "root");
		PassWord=prop.getProperty("pass_word", "");
		MysqlUrl=prop.getProperty("mysql_url", "jdbc:mysql://localhost:3306/");
	}

	public static String getUserName()
	{
		return UserName;
	}

	public static String getPassWord()
	{
		return PassWord;
	}

	public static String getMysqlUrl()
	{
		return MysqlUrl;
	}
   
}
