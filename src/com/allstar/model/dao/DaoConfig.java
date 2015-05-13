package com.allstar.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	public final static String DROP_TABLE="drop table ?";
    public final static String USE_DATABASE="use ?";
    public final static String CREATE_TABLE_ASSIGN_NUMBER_TO_UNIVERSITY="CREATE TABLE `uni_name_number` (`UniNumber` INT(11) NOT NULL AUTO_INCREMENT,`UniName` VARCHAR(30) DEFAULT NULL UNIQUE,PRIMARY KEY (`UniNumber`)) ENGINE=INNODB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC";
	public final static String CREATE_TABLE_WEIGHT_OF_UNIVERSITY="CREATE TABLE `weight_of_university` (`UniNumber` int(11) NOT NULL,`sgima` DOUBLE NOT NULL,`mu` DOUBLE NOT NULL,`year` INT(4) NOT NULL, `total` int(11) NOT NULL,`index` int(11) NOT NULL,`clazz` tinyint(4) NOT NULL,`rank` int(11) NOT NULL,PRIMARY KEY (`UniNumber`,`year`,`clazz`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC";
    public final static String INSERT_INTO_ASSIGN_NUMBER_TO_UNIVERSITY="INSERT INTO `gk`.`uni_name_number`(`UniName`) VALUES (?)";
    public final static String INSERT_TABLE_WEIGHT_OF_UNIVERSITY="INSERT INTO `gk`.`weight_of_university`(`UniNumber`,`sgima`,`mu`,`year`,`total`,`index`,`clazz`,`rank`) VALUES ( ?,?,?,?,?,?,?,?);";
	public final static String QUERY_ALL_UNVERSITY="select YXMC from ?";
	public final static String QUERY_NUMBER_TO_UNIVERSITY="SELECT * FROM uni_name_number WHERE UniName = ?";
	public final static String QUERY_ALL_NUMBER_TO_UNIVERSITY="SELECT * FROM uni_name_number";
	public final static String QUERY_ALL_INFO_BY_UNIVERSITY="select * from ? where YXMC = ?";
	public final static String QUERY_ALL_INFO_ORDER_BY_UNIVERSITY="select * from ? order by YXMC";
	public static void init() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("setting.properties");
		prop.load(fis);
		UserName=prop.getProperty("user_name", "root");
		PassWord=prop.getProperty("pass_word", "");
		MysqlUrl=prop.getProperty("mysql_url", "jdbc:mysql://localhost:3306/");
	    fis.close();	    
	}
    
	public static void modify(String username,String password,String url) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("setting.properties");
	    prop.load(fis);
	    fis.close();
	    prop.setProperty("user_name", username);
	    prop.setProperty("pass_word", password);
	    prop.setProperty("mysql_url", url);
	    FileOutputStream fos = new FileOutputStream("setting.properties");
	    prop.store(fos,"");
	    fos.close();
	    UserName=username;
	    PassWord=password;
	    MysqlUrl=url;
	   
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
