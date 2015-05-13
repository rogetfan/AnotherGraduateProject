package com.allstar.view;

import java.io.IOException;

import com.allstar.MainApp;
import com.allstar.handle.InitAssignNumberTableHandler;
import com.allstar.handle.InitWeightOfUniversityHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootLayController 
{
    @FXML 
    private MenuItem setting;
    @FXML 
    private MenuItem about;
    @FXML 
    private MenuItem iniTable1;
    @FXML 
    private MenuItem iniTable2;
    
    
    
    @FXML
    private void displaySetting()
    {
    	
    	try {
    		FXMLLoader loader =new FXMLLoader();
        	loader.setLocation(MainApp.class.getResource("view/ModifyDaoConfig.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage ModifyDao = new Stage();
			ModifyDao.setTitle("更改数据库配置");
			ModifyDao.initModality(Modality.WINDOW_MODAL);
		    ModifyDaoConfigController control = loader.getController();
		    control.setSelf(ModifyDao);
		    Scene scene = new Scene(page);
		    ModifyDao.setScene(scene);
		    ModifyDao.show();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    private void modifyTable1()
    {
    	
	   InitAssignNumberTableHandler.work();
		
    }
    @FXML
    private void modifyTable2()
    {
    	InitWeightOfUniversityHandler.work();
    }
    
}
