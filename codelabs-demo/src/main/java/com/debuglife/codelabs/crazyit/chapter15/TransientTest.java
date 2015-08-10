package com.debuglife.codelabs.crazyit.chapter15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TransientTest {
	public static void main(String[] args) throws Exception{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/home/roger/test/person1.txt"));
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/home/roger/test/person1.txt"));

		Person1 p = new Person1("roger", 20);
		oos.writeObject(p);
		
		Person1 pp = (Person1)ois.readObject();
		System.out.println(pp.getName());
		System.out.println(pp.getAge());
		
		oos.close();
		ois.close();
	}
}

class Person1 implements Serializable{
	private static final long serialVersionUID = -7503204441119443123L;
	private String name;
	private transient int age;
	
	public Person1(String name, int age){
		System.out.println("construction method is invoked.");
		this.name = name;
		this.age = age;
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