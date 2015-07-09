package com.debuglife.codelabs;

import java.util.HashMap;
import java.util.Map;

public class TestObjectEqual {
	public static void main(String[] args){
		String str1 = new String("hello");
		String str2 = new String("hello");
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));
		System.out.println(str1.hashCode() == str2.hashCode());
		
		String str3 = str1;
		System.out.println(str3.equals(str1));
		System.out.println(str3.hashCode() == str2.hashCode());
		
		// determine two objects is equal
		if(str1 == str2 || (str1.equals(str2) && (str1.hashCode() == str2.hashCode()))){
			
		}
		
		Map m = new HashMap();
	}
}
