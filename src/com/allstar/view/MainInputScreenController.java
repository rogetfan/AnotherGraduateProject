package com.allstar.view;

import java.io.IOException;

import com.allstar.MainApp;
import com.allstar.model.LineChartInstance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
		Sci_Ba.setPromptText("选择学科");
	}
	@FXML
	private void onClickOk()
	{
	  int clazz= Sci_Ba.getSelectionModel().getSelectedItem().equals("理科")?95:91;
	  try 
	  {
		  FXMLLoader loader =new FXMLLoader();
		  loader.setLocation(MainApp.class.getResource("view/DisplayLineChart.fxml"));
		  AnchorPane page = (AnchorPane) loader.load();
		  LineChartInstance    lci =  new LineChartInstance(); 
		     lci.setStatis(2009, 0.8);
		     lci.setStatis(2010, 0.5);
		     lci.setStatis(2011, 0.4);
		     lci.setStatis(2012, 0.9);
		     lci.setStatis(2013, 0.1);
		     lci.setStatis(2014, 0.4);
		     lci.setStatis(2015, 0.7);
		     CategoryAxis   xAxis = new CategoryAxis();
		     NumberAxis  yAxis = new NumberAxis();
		     xAxis.setLabel("Month"); 
		     LineChart   lineChart = new LineChart<String,Number>(xAxis,yAxis);  
		     lineChart.setTitle("高校历年录取指数");
		     XYChart.Series series = new XYChart.Series();
		     series.setName("指数线");
		     series.getData().add(new Data("2009", lci.getStatis()[0]));
		     series.getData().add(new Data("2010", lci.getStatis()[1]));
		     series.getData().add(new Data("2011", lci.getStatis()[2]));
		     series.getData().add(new Data("2012", lci.getStatis()[3]));
		     series.getData().add(new Data("2013", lci.getStatis()[4]));
		     series.getData().add(new Data("2014", lci.getStatis()[5]));
		     series.getData().add(new Data("2015", lci.getStatis()[6]));
		     System.out.println(series);
		     lineChart.getData().add(series); 
		  
		  
		  
		  
		  
		  
		  Scene scene  = new Scene(page);
	      Stage stage =  new Stage();
		  stage.setTitle("预测结果");
	      stage.setScene(scene);
	      stage.show();
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
 @FXML
 private void onClickCancel()
 {
	self.close();
 }
}
