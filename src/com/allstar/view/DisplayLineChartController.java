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
	     this.lci=lci;
	     lineChart.setTitle("高校历年录取指数");
	     XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
	     series.setName("指数线");
	     series.getData().add(new Data<String, Number>("2009", lci.getStatis()[0]));
	     series.getData().add(new Data<String, Number>("2010", lci.getStatis()[1]));
	     series.getData().add(new Data<String, Number>("2011", lci.getStatis()[2]));
	     series.getData().add(new Data<String, Number>("2012", lci.getStatis()[3]));
	     series.getData().add(new Data<String, Number>("2013", lci.getStatis()[4]));
	     series.getData().add(new Data<String, Number>("2014", lci.getStatis()[5]));
	     series.getData().add(new Data<String, Number>("2015", lci.getStatis()[6]));
	     lineChart.getData().add(series); 
   }
	
	
   @FXML
   private void initialize()
   { 
    
   }
}
