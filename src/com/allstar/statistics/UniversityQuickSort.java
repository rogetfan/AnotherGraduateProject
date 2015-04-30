package com.allstar.statistics;

import java.util.ArrayList;

public class UniversityQuickSort
{
	static  final void quick_sort(ArrayList<University> arrays,int begin,int end)
	{
          if(begin<end){
        	  int q =partition(arrays, begin,end);
        	  quick_sort(arrays,begin,q-1);
        	  quick_sort(arrays,q+1,end);
          }
	}

	static final int partition(ArrayList<University> arrays,int begin,int end)
	{
		int i;
		int j;
		University x;
		x = arrays.get(end);
		//System.out.println("end:"+x.getMu());
		for (j = begin, i = begin-1; j < end; j++)
		{
			if (arrays.get(j).getMu() <= x.getMu()&&i++<end)
			{			
				if (i == j){}				
				else
				{
					University temp = arrays.get(j);
					arrays.set(j, arrays.get(i));
					arrays.set(i, temp);
				}
			}
		}
		University temp= arrays.get(i+1);
		arrays.set(i+1, arrays.get(end));
		arrays.set(end, temp);
		return i+1;
	}
}