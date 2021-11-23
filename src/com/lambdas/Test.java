package com.lambdas;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Test {

	@FunctionalInterface
	interface T 
	{
		void sayHello();
	}
	
	static void hello(T t)
	{
		t.sayHello();
	}
	
	interface Deserializer {
	    public int deserialize(String v1);
	    
	    default void say()
	    {
	    	System.out.println("hi");
	    }
	}
	
	class M implements Deserializer
	{

		@Override
		public int deserialize(String v1) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public void say() {
//			Deserializer.super.say();
			System.out.println("hello");
		}
		
	}
	
	static class StringConverter {
	    public int convertToInt(String v1){
	        return Integer.valueOf(v1);
	    }
	}
	
	public static void main(String[] args) {
		
		
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .map(s -> {
	        System.out.println("map: " + s);
	        return s.toUpperCase();
	    })
	    .noneMatch(s -> {
	        System.out.println("anyMatch: " + s);
	        return s.startsWith("A");
	    });
		
		//method references
		Test t1 = new Test();
		t1.new M().say();
		StringConverter stringConverter = new StringConverter();
		Deserializer des =  stringConverter::convertToInt;
		des.deserialize("211");
		
		Predicate<String> startsWithA = (text) -> text.startsWith("A");
		
		String name = "Mansoor";
		T t = () -> {
			System.out.println("hello " + name);};
		hello(t);
		hello(new T(){
			private String a = "";
			@Override
			public void sayHello() {
				System.out.println("hi");
			}});
	}
}
