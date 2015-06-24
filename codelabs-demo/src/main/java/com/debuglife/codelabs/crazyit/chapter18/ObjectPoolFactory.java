package com.debuglife.codelabs.crazyit.chapter18;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectPoolFactory {
	
	private Map<String, Object> objectPool = new HashMap<>();
	
	public Object createObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> clazz = Class.forName(className);
		return clazz.newInstance();
	}
	
	public void initPool(String fileName) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
//		InputStream is = ObjectPoolFactory.class.getResourceAsStream(fileName);
//		InputStream is = this.getClass().getResourceAsStream(fileName);
//		InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName); // it doesn't work!!!
		InputStream is = this.getClass().getResourceAsStream(fileName);
		FileInputStream fis = null;
		try {
			//fis = new FileInputStream(fileName);
			Properties p = new Properties();
			p.load(is);
			for(String name : p.stringPropertyNames()){
				objectPool.put(name, createObject(p.getProperty(name)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(fis != null){
					fis.close();
				}
			} catch (IOException e) {
				//ignore
			}
		}
	}
	
	public Object getObject(String name){
		return objectPool.get(name);
	}
	
	public static void main(String[] args) throws Exception{
		ObjectPoolFactory objectPoolFactory = new ObjectPoolFactory();
		objectPoolFactory.initPool("class.properties");
		System.out.println(objectPoolFactory.getObject("date"));
		System.out.println(objectPoolFactory.getObject("jframe"));
	}
}
