package com.debuglife.codelabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestComparable {
	public static void main(String[] args){
		Person p1 = new Person(1,"san","zhang");
		Person p2 = new Person(4,"si","li");
		Person p3 = new Person(3,"liu","wang");
		
		List list = new ArrayList();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
	}
}

class Person implements Comparable<Object>{
	private int id;
	private String firstName;
	private String lastName;
	
	Person(int id, String firstName, String lastName){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String toString(){
		return id + "_" + firstName + " " + lastName;
	}
	
	@Override
	public boolean equals(Object obj){
	    if(obj == null)
	        return false;
		if(obj != null && obj.getClass() == Person.class){
			Person c =  (Person)obj;
			if(c.firstName == this.firstName 
					&& c.lastName == this.lastName){
			    return true;
			}
			return false;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
	    int prime = 31;
	    int result = 1;
	    result = prime * result + (firstName == null ? 0 : firstName.hashCode()) + (lastName == null ? 0 : lastName.hashCode());
	    return result;
	}
	
	
	public int compareTo(Object obj){
		return !(obj instanceof Person)? -1 : (this.id -((Person)obj).id);
	}
}