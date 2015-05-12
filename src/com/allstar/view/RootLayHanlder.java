package com.allstar.view;

import java.io.IOException;

import com.allstar.MainApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootLayHanlder 
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
			Stage dialogStage = new Stage();
		    dialogStage.setTitle("更改数据库配置");
		    dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
