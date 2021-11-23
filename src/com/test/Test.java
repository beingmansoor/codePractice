package com.test;

import java.util.function.BiFunction;

public class Test
{

	public Test t() throws Exception
	{
		return null;
	}

	class Bike
	{
		int speedlimit = 90;
	}

	class Honda3 extends Bike
	{
		int speedlimit = 150;

	}

	public static int add(int a, int b)
	{
		return a + b;
	}

	public static void main(String args[])
	{
		BiFunction<Integer, Integer, Integer> t = Test::add;
		Bike obj = new Test().new Honda3();
		System.out.println(obj.speedlimit);// 90
	}
}

class B extends Test
{
	public B t() throws RuntimeException
	{
		return null;
	}
}
