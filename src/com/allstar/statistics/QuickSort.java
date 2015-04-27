package com.allstar.statistics;

/**
 * 快速排序算法
 * */

public class QuickSort
{
	static  final void quick_sort(float[] arrays,int begin,int end)
	{
          if(begin<end){
        	  int q =partition(arrays, begin,end);
        	  quick_sort(arrays,begin,q-1);
        	  quick_sort(arrays,q+1,end);
          }
	}

	static final int partition(float[] arrays,int begin,int end)
	{
		int i;
		int j;
		float x;
		x = arrays[end];
		for (j = begin, i = begin-1; j < end; j++)
		{
			if (arrays[j] <= x&&i++<end)
			{			
				if (i == j){}				
				else
				{
					float temp = arrays[j];
					arrays[j] = arrays[i];
					arrays[i] = temp;
				}
			}
		}
		float temp = arrays[i + 1];
		arrays[i + 1] = arrays[end];
		arrays[end] = temp;
		return i+1;
	}
}
