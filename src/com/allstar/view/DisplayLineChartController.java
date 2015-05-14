package com.allstar.view;

import com.allstar.model.LineChartInstance;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class DisplayLineChartController 
{
   
   LineChartInstance lci;
   @FXML
   LineChart<String,Number> lineChart; 
   @FXML
   CategoryAxis xAxis;
   @FXML
   NumberAxis yAxis;
	
   public void setStatistics(LineChartInstance lci)
   {
	     lci =  new LineChartInstance(); 
	     lci.setStatis(2009, 0.8);
	     lci.setStatis(2010, 0.5);
	     lci.setStatis(2011, 0.4);
	     lci.setStatis(2012, 0.9);
	     lci.setStatis(2013, 0.1);
	     lci.setStatis(2014, 0.4);
	     lci.setStatis(2015, 0.7);
	     lineChart = new LineChart<String,Number>(xAxis,yAxis);  
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
	     this.lci=lci;
   }
	
	
   @FXML
   private void initialize()
   { 
     xAxis = new CategoryAxis();
     yAxis = new NumberAxis();
     xAxis.setLabel("Month"); 
    
   }
}
