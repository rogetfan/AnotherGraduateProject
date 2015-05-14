package com.allstar.statistics;

import java.util.List;

import com.allstar.model.StatisResult;

/**
 * 求解最小二乘法
 * y=ax+b
 * **/

public class LeastSquares {
	private List<StatisResult> list;
	private double a;
	private double b;

	public LeastSquares(List<StatisResult> list) {
		this.list = list;
	}

	public void analyse() {
		double t1 = 0, t2 = 0, t3 = 0, t4 = 0;
		for (StatisResult u : list) {
			double point = u.getPoint();
			double year = u.getYear();
			t1 += year * year;
			t2 += year;
			t3 += year * point;
			t4 += point;
		}
		a=(t3*list.size()-t2*t4)/(t1*list.size()-t2*t2);
		b=(t1*t4-t2*t3)/(t1*list.size()-t2*t2);
	}

	public double getA() {
		return this.a;
	}

	public double getB() {
		return this.b;
	}
}
