package com.allstar.statistics;

import java.util.List;

/**
 * 最大似然估计法
 * */

public class MaximumLikelihood
{
	private double sigma;
	private double mu;
	private List<Double> points;

	public double getSigma()
	{
		return sigma;
	}

	public double getMu()
	{
		return mu;
	}
	
	public MaximumLikelihood(List<Double> points)
	{
		 this.points=points;
	}
	public void analyse()
	{
	   int length=points.size();
	   double total1=0;
	   double total2=0;
	   for(Double d: points)
	   {
		   total1=d+total1;
	   }
	   mu=total1/length;
	   for(Double d: points)
	   {
		   total2=(d-mu)*(d-mu)+total2;
	   }
	   sigma=(float) Math.sqrt(total2);
	   
	}
}
