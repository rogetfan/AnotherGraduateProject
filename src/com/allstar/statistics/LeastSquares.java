package com.allstar.statistics;

import java.util.List;

public class LeastSquares {
	private List<University> list;
	private double a;
	private double b;

	public LeastSquares(List<University> list) {
		this.list = list;
	}

	public void analyse() {
		double t1 = 0, t2 = 0, t3 = 0, t4 = 0;
		for (University u : list) {
			double point = u.getMu();
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
