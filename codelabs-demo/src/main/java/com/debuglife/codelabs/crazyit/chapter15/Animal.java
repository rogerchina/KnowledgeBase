package com.debuglife.codelabs.crazyit.chapter15;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Animal implements Externalizable {

	private String name;
	private int age;
	
	public Animal(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(new StringBuffer(name).reverse());
		out.writeInt(age);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		this.name = ((StringBuffer)in.readObject()).reverse().toString();
		this.age = in.readInt();
	}

}
