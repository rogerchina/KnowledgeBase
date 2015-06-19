package com.debuglife.codelabs.crazyit.chapter18;

import java.net.URL;
import java.util.Enumeration;

public class ExtentionClassLoaderTest { 
	public static void main(String[] args) throws Exception{
		//系统（应用）类加载器
		//主要加载jvm启动时来自java命令的-classpath选项，java.class.path系统属性，
		//或CLASSPATH环境变量所指定的jar包和类路径
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println("系统类加载器: " + systemClassLoader);
		Enumeration<URL> eml = systemClassLoader.getResources("");
		while(eml.hasMoreElements()){
			System.out.println(eml.nextElement());
		}
		
		//扩展类加载器
		//主要负责加载jre的扩展目录（％JAVA_HOME％/jre/lib/ext或者由java.ext.dirs系统属性指定的目录）中的JAR包中的类
		//获取系统类加载器的父类加载器，得到扩展类加载器
		ClassLoader extentionClassLoader = systemClassLoader.getParent();
		Enumeration<URL> extEml = extentionClassLoader.getResources("");
		while(extEml.hasMoreElements()){
			System.out.println(extEml.nextElement());
		}
		System.out.println("系统类加载器的父类加载器，即扩展类加载器: " + extentionClassLoader);
		System.out.println("系统类加载器的父类加载器，即扩展类加载器的加载路径: " + System.getProperty("java.ext.dirs"));
		System.out.println("系统类加载器的父类加载器父类加载器，即扩展类加载器的父类加载器: " + extentionClassLoader.getParent());
	}
}
