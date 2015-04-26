package com.allstar.statistics;

public class University {
	private String university_name;
	private String university_number;
	private double mu;
	private double sigma;
	private int year;
	private int index;
	private int total;
	//文科 true
	//理科 false
	private int clazz;

	public int getYear() 
	{
		return year;
	}

	public void setYear(int year) 
	{
		this.year = year;
	}
	
	public String getUniversity_name() 
	{
		return university_name;
	}

	public void setUniversity_name(String university_name) 
	{
		this.university_name = university_name;
	}

	public String getUniversity_number() 
	{
		return university_number;
	}

	public void setUniversity_number(String university_number) 
	{
		this.university_number = university_number;
	}

	public double getMu() 
	{
		return mu;
	}

	public void setMu(double mu) 
	{
		this.mu = mu;
	}

	public double getSigma() 
	{
		return sigma;
	}

	public void setSigma(double sigma) 
	{
		this.sigma = sigma;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int isClazz() {
		return clazz;
	}

	public void setClazz(int clazz) {
		this.clazz = clazz;
	}

}
