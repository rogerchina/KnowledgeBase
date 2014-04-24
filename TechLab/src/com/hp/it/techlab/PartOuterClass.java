package com.hp.it.techlab;

public abstract class PartOuterClass {
	private int i = 10;
	public abstract int addValue();

	public static void main(String[] args){
		System.out.println(new PartOuterClass(){
			int j;
			@Override
			public int addValue() {
				j++;
				return 0;
			}
			
		});
	}
}
