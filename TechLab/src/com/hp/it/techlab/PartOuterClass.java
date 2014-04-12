package com.hp.it.techlab;

public class PartOuterClass {
	private int i = 10;
	public int addValue(){
		final String s = "innerclass";
		class methodInnerClass {
			public void getValue(){
				i++;
				System.out.println(s);
			}
		}
		methodInnerClass mic = new methodInnerClass();
		mic.getValue();
		return i;
	}
	
	public static void main(String[] args){
		PartOuterClass outer = new PartOuterClass();
		System.out.println(outer.addValue());
	}
}
