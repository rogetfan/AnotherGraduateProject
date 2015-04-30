package com.allstar.statistics;

import java.util.ArrayList;

public class testQsort
{
   public static void main(String args[])
   {
	   University u1 =new University();
	   u1.setMu(638.1);
	   University u2 =new University();
	   u2.setMu(673.9);
	   University u3 =new University();
	   u3.setMu(628.3);
	   University u4 =new University();
	   u4.setMu(699.9);
	   University u5 =new University();
	   u5.setMu(509.1);
	   University u6 =new University();
	   u6.setMu(409.5);
	   University u7 = new University();
	   u7.setMu(897.13);
	   ArrayList<University> list=new ArrayList<University>();
	   list.add(u1);
	   list.add(u2);
	   list.add(u3);
	   list.add(u4);
	   list.add(u5);
	   list.add(u6);
	   list.add(u7);
	   UniversityQuickSort.quick_sort(list, 0,5);
	   for(University s:list)
	   {
		   System.out.println(s.getMu());
	   }
   }
}
