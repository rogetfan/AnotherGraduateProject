package com.allstar.view;

import java.io.IOException;

import com.allstar.model.dao.DaoConfig;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyDaoConfigController 
{
	    @FXML
	    private TextField UserName;
	    @FXML
	    private TextField PassWord;
	    @FXML
	    private TextField MysqlUrl;
	    
	    private String user_name;
	    private String pass_word;
	    private String mysql_url;
	    
	    private Stage self;
	    
	    @FXML   
	    private void initialize()
	    {
	    	try 
	    	{
				DaoConfig.init();
			} 
	    	catch (IOException e) 
	    	{
				System.err.println("Initialize database configuration error");
				e.printStackTrace();
			}
	    	UserName.setText(DaoConfig.getUserName());
	    	PassWord.setText(DaoConfig.getPassWord());
	    	MysqlUrl.setText(DaoConfig.getMysqlUrl());
	    	
	    }
	    
	    public void setSelf(Stage self)
	    {
	    	this.self=self;
	    }
	    @FXML
	    private void OnClickOk()
	    {
	    	if(UserName.getText()==null||UserName.getText().length()==0)
	    	{
	    		user_name="";	
	    	}
	    	else
	    	{
	    		user_name=UserName.getText();
	    	}
	    	if(PassWord.getText()==null|PassWord.getText().length()==0)
	    	{
	    		pass_word="";
	    	}
	    	else
	    	{
	           pass_word=PassWord.getText();		
	    	}
	    	if(MysqlUrl.getText()==null|MysqlUrl.getText().length()==0)
	    	{
	    		mysql_url="";
	    	}
	    	else
	    	{
	    		mysql_url=MysqlUrl.getText();
	    	}
	    	try 
	    	{
				DaoConfig.modify(user_name, pass_word, mysql_url);
			} 
	    	catch (IOException e) 
	    	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println(DaoConfig.getUserName());
	    	System.out.println(DaoConfig.getPassWord());
	    	System.out.println(DaoConfig.getMysqlUrl());
	    	
	    	self.close();
	    }
	    @FXML
	    private void onClickCancel()
	    {
	    	self.close();
	    }
	    
}
