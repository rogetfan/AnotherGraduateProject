package com.allstar.model;
/**
 * 大学实例
 * */

public class University {
	private String university_name;
	private int university_number;
	private double mu;
	private double sigma;
	private int year;
	//人数排名起始位置
	private int index;
	//总人数
	private int total;
	//文科 91
	//理科 95
	private int clazz;
	//该年次排名
	private int rank;

	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("\nuniversity_name:");
		sb.append(university_name);
		sb.append("\nuniversity_number:");
		sb.append(university_number);
		sb.append("\nmu:");
		sb.append(mu);
		sb.append("\nsigma:");
		sb.append(sigma);
		sb.append("\nyear:");
		sb.append(year);
		sb.append("\nindex:");
		sb.append(index);
		sb.append("\ntotal:");
		sb.append(total);
		sb.append("\nclazz:");
		sb.append(clazz);
		sb.append("\nrank:");
		sb.append(rank);
		return sb.toString();
	}
	
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

	public int getUniversity_number() 
	{
		return university_number;
	}

	public void setUniversity_number(int university_number) 
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

	public int getClazz() {
		return clazz;
	}

	public void setClazz(int clazz) {
		this.clazz = clazz;
	}

	public int getRank()
	{
		return rank;
	}

	public void setRank(int rank)
	{
		this.rank = rank;
	}

}
