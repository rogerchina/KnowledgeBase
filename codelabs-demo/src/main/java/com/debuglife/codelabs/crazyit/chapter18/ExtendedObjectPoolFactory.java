package com.debuglife.codelabs.crazyit.chapter18;

import java.awt.Color;
import java.awt.Frame;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ExtendedObjectPoolFactory {
	
	private Map<String, Object> objectPool = new HashMap<>();
	private Properties props = new Properties();
	
	public Object createObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> clazz = Class.forName(className);
		return clazz.newInstance();
	}
	
	public void init(String fileName) throws Exception{
		InputStream is = this.getClass().getResourceAsStream(fileName);
		props.load(is);
	}
	
	public void initPool() {
		try {
			for(String name : props.stringPropertyNames()){
				if(!name.contains("%")){
					objectPool.put(name, createObject(props.getProperty(name)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void initProperties() throws Exception{
		for(String name : props.stringPropertyNames()){
			if(name.contains("%")){
				String[] objAndProp = name.split("%");
				Object target = getObject(objAndProp[0]);
				String methodName = "set" + objAndProp[1].substring(0, 1).toUpperCase() + objAndProp[1].substring(1);
				Class<?> clazz = target.getClass();
				Method method = clazz.getMethod(methodName, String.class);
				method.invoke(target, props.getProperty(name));
			}
		}
	}
	
	public Object getObject(String name){
		return objectPool.get(name);
	}
	
	public static void main(String[] args) throws Exception{
		ExtendedObjectPoolFactory objectPoolFactory = new ExtendedObjectPoolFactory();
		objectPoolFactory.init("class.properties");
		objectPoolFactory.initPool();
		objectPoolFactory.initProperties();
		
		System.out.println(objectPoolFactory.getObject("jframe"));
		Frame f = (Frame)objectPoolFactory.getObject("jframe");
		f.setBackground(Color.blue);
		f.setLocation(300, 300);
		f.setSize(170,100);
		f.setVisible(true);
	}
}
