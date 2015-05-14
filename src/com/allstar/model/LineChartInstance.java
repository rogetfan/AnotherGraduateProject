package com.allstar.model;

public class LineChartInstance 
{
    
	private double[] statis = new double[7];
	
	public LineChartInstance()
	{
		for(int i =0;i<7;i++)
		{
			statis[i]=(double)0;	
		}
	}
	public double[] getStatis()
	{
		return statis;
	}
	public void setStatis(int year,double statis)
	{
		if(year==2009)
		{
			this.statis[0]=statis;
		}
		else if(year==2010)
		{
			this.statis[1]=statis;
		}
		else if(year==2011)
		{
			this.statis[2]=statis;
		}
		else if(year==2012)
		{
			this.statis[3]=statis;
		}
		else if(year==2013)
		{
			this.statis[4]=statis;
		}
		else if(year==2014)
		{
			this.statis[5]=statis;
		}
		else if(year==2015)
		{
			this.statis[6]=statis;
		}
	}
}
