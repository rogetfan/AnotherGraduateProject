package com.allstar.statistics;

public class MaximumLikelihood
{
	private float sigma;
	private float mu;
	private float[] points;

	public float getSigma()
	{
		return sigma;
	}

	public float getMu()
	{
		return mu;
	}
	
	public MaximumLikelihood(float[] points)
	{
		 this.points=points;
	}
	public void analyse()
	{
	   int length=points.length;
	   float total1=0;
	   float total2=0;
	   for(int i =0;i<length;i++)
	   {
		   total1=points[i]+total1;
	   }
	   sigma=total1/length;
	   for(int i=0;i<length;i++)
	   {
		   total2=(points[i]-sigma)*(points[i]-sigma)+total2;
	   }
	   mu=(float) Math.sqrt(total2);
	   
	}
	
	public static void main(String args[])
	{
		float[] f={(float)678,(float) 678,(float)678};
		MaximumLikelihood ml=new MaximumLikelihood(f);
		ml.analyse();
		System.out.println(ml.getMu());
		System.out.println(ml.getSigma());
	}
}
