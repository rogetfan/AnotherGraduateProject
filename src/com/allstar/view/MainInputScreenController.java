package com.allstar.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainInputScreenController 
{
   
	
	@FXML
	private TextField SchoolName;
	@FXML
	private ComboBox<String> Sci_Ba;
	
	Stage self;
	
	public void setSelf(Stage self)
	{
		this.self=self;
	}
	
	@FXML
	private void initialize()
	{
		Sci_Ba.getItems().addAll("文科","理科");
		Sci_Ba.setPromptText("理科");
	}
	@FXML
	private void onClickOk()
	{
	  int clazz= Sci_Ba.getSelectionModel().getSelectedItem().equals("理科")?95:91;
	  
	}
	@FXML
	private void onClickCancel()
	{
		self.close();
	}
}
