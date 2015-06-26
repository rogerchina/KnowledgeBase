package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.reflect.Field;

public class FieldTest {
	public static void main(String[] args) throws Exception{
		Person p = new Person();
		Class<Person> clazz = Person.class;
		Field nameField = clazz.getDeclaredField("name");
		nameField.setAccessible(true);
		nameField.set(p, "Roger.K.Zhang");
		
		Field ageField = clazz.getDeclaredField("age");
		ageField.setAccessible(true);
		ageField.set(p, 18);
		System.out.println(p);
	}
}

class Person{
	private String name;
	private int age;
	@Override
	public String toString() {
		return "Person[name:" + name + " , age:" + age + "]";
	}
}
