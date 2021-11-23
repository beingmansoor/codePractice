package com.java;

class Sample
{
	void f1(String s1)
	{
		System.out.println("Sample.f1() with string");
	}

	void f1(Object s1)
	{
		System.out.println("Sample.f1() with object");
	}
}

class Sample2 extends Sample
{
	void f1(String s1)
	{
		System.out.println("Derived :: Sample.f1() with string");
	}

	void f1(Object s1)
	{
		System.out.println("Derived :: Sample.f1() with object");
	}
}

public class TestOverriding
{
	public static void main(String[] args)
	{
		String test= "mansoor";
		String obj = test;
		
		Sample2 sample2 = new Sample2();
		sample2.f1(test);
		sample2.f1(obj);
		
		Sample sm =  new Sample2();
		sm.f1(test);
		sm.f1(obj);
		
	}
}
