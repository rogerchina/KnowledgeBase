package com.debuglife.codelabs.crazyit.chapter15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ReadWriteObjectTest {
	public static void main(String[] args) throws Exception{
		writeObject();
		readObject();
	}
	
	public static void writeObject() throws Exception{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/home/roger/test/object.txt"));
		Person p = new Person("roger", 20);
		oos.writeObject(p);
	}
	
	public static void readObject() throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/home/roger/test/object.txt"));
		Person p = (Person)ois.readObject();
		System.out.println(p.getName() + " " + p.getAge());
	}
}

class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	
	public Person(String name, int age){
		this.name = name;
		this.age = age;
		System.out.println("constructor method with arguments");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
