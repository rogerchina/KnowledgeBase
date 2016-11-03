package com.debuglife.codelabs.corejava;

import java.util.Random;

public class TestThreadLocal implements Runnable{
	private final static ThreadLocal<Student> threadLocal = new ThreadLocal<Student>();
	public static void main(String[] args){
		TestThreadLocal t1 = new TestThreadLocal();
		TestThreadLocal t2 = new TestThreadLocal();
		
		Thread thread1 = new Thread(t1, "thread1");
		Thread thread2 = new Thread(t2, "thread2");
		
		thread1.start();
		thread2.start();
	}
	
	public void run(){
		accessStudent();
	}
	
	private void accessStudent(){
	    String currentThreadName = Thread.currentThread().getName();
            System.out.println(currentThreadName + " is running!");
            //generate a random number
            Random random = new Random();
            int age = random.nextInt(100);
            System.out.println("thread " + currentThreadName + " set age to:" + age);
            //get a Student object and set age's value
            Student student = getStudent();
            student.setAge(age);
            System.out.println("thread " + currentThreadName + " first read age is:" + student.getAge());
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("thread " + currentThreadName + " second read age is:" + student.getAge());
	}
	
	private Student getStudent(){
		Student student = threadLocal.get();
		if(student == null){
			student = new Student();
			threadLocal.set(student);
		}
		return student;
	}
}

class Student{
	private int age = 0;
	
	public void setAge(int age){
		this.age = age;
	}
	
	public int getAge(){
		return age;
	}
}

