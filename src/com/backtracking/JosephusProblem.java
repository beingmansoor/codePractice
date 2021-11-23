package com.backtracking;

public class JosephusProblem {

	
	static int luckySoldier(int n,int k)
	{
		if(n == 1)
			return 0;
		int servier = luckySoldier(n-1, k);
		return (servier + k) %n;
	}
	
	public static void main(String[] args) {
		System.out.println(luckySoldier(4, 2));
		System.out.println(luckySoldier(8, 3));
	}
}
