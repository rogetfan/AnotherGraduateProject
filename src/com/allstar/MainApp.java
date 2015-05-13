package com.allstar;

import java.io.IOException;

import com.allstar.view.MainInputScreenController;

import sun.applet.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application
{
	private Stage primaryStage;
	private BorderPane rootLayout;
	@Override
	public void start(Stage primaryStage) 
	{
		
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("高校录取统计");
		initRootLay();
		initMainInputScreen();
	}
	
	private void initRootLay()
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLay.fxml"));
			rootLayout = (BorderPane)loader.load();
			Scene scene =  new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initMainInputScreen()
	{
		
	    try {
	    	FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(MainApp.class.getResource("view/MainInputScreen.fxml"));
			AnchorPane input = (AnchorPane) loader.load();
			rootLayout.setCenter(input);
			MainInputScreenController control = loader.getController();
			control.setSelf(primaryStage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}

}
