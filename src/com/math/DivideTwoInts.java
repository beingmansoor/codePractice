package com.math;

public class DivideTwoInts
{
	public static int divide(int dividend, int divisor)
	{

		if (dividend == Integer.MIN_VALUE && divisor == -1)
		{
			return Integer.MAX_VALUE;
		}
		int sign = 1;
		if (dividend < 0)
		{
			sign *= -1;
		}
		if (divisor < 0)
		{
			sign *= -1;
		}

		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		if (divisor == 1)
		{
			return sign * dividend;
		}

		int factor = 0;
		if (dividend != Integer.MIN_VALUE)
		{
			while (dividend >= divisor)
			{
				dividend = dividend - divisor;
				factor++;
			}
		}
		else
		{
			while(dividend < divisor)
			{
				dividend = dividend+divisor;
				factor++;
			}
		}

		return factor * sign;

	}

	public static void main(String[] args)
	{
		System.out.println(Integer.MIN_VALUE);
		 System.out.println(divide(-2147483648, 2));
		System.out.println(divide(7, -3));
		System.out.println(divide(0, 1));
		System.out.println(divide(1, 1));
	}
}
