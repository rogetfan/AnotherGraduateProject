package com.allstar.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.allstar.MainApp;
import com.allstar.model.LineChartInstance;
import com.allstar.model.StatisResult;
import com.allstar.model.dao.DaoConfig;
import com.allstar.model.dao.DaoInstance;
import com.allstar.statistics.LeastSquares;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	}
	@FXML
	private void onClickOk()
	{
	  int clazz= Sci_Ba.getSelectionModel().getSelectedItem().equals("理科")?95:91;
	  String school = SchoolName.getText();
	  DaoInstance di;
	  try 
	  {
		DaoConfig.init();
	  }
	  catch (IOException e3) 
	  {
		System.err.println("Initialize database configuration error");
		e3.printStackTrace();
	  }
      di =new DaoInstance("gk");
	  try
	  {
		di.connect();
	  }
	  catch (SQLException e)
 	  {
		System.err.println("Connect to DataBase Error");
		e.printStackTrace();
	  }
	  List<StatisResult> list=di.query_all_result_from_university_weight(school, clazz);
	  LineChartInstance    lci =  new LineChartInstance(); 
	  for(StatisResult s:list)
	  {
		  int total = 0;
			try {
				if (s.getYear() == 2009)
					total = di.query_quantity_from_lqk("lqk09", s.getIndex()+s.getTotal(), school);
				else if (s.getYear() == 2010)
					total = di.query_quantity_from_lqk("lqk10", s.getIndex()+ s.getTotal(), school);
				else if (s.getYear() == 2011)
					total = di.query_quantity_from_lqk("lqk11", s.getIndex()+ s.getTotal(), school);
				else if (s.getYear() == 2012)
					total = di.query_quantity_from_lqk("lqk12", s.getIndex()+ s.getTotal(), school);
				else if (s.getYear() == 2013)
					total = di.query_quantity_from_lqk("lqk13", s.getIndex()+ s.getTotal(), school);
				else if (s.getYear() == 2014)
					total = di.query_quantity_from_lqk("lqk14", s.getIndex()+ s.getTotal(), school);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  s.setPoint((double)total/(double)(s.getIndex()+s.getTotal()));
		  lci.setStatis(s.getYear(), s.getPoint());
	  }
	  LeastSquares ls = new LeastSquares(list);
	  ls.analyse();
	  lci.setStatis(2015, ls.getA()*2015+ls.getB());
	  try 
	  {
		  FXMLLoader loader =new FXMLLoader();
		  loader.setLocation(MainApp.class.getResource("view/DisplayLineChart.fxml"));
		  AnchorPane page = (AnchorPane) loader.load();
		  DisplayLineChartController control = loader.getController();
		  control.setStatistics(lci);
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
