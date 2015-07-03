package com.debuglife.codelabs.crazyit.chapter18;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class EvalTest {
	public static void main(String[] args){
		System.out.println(EvalTest.class.getClass().getResource("/").getPath());
		System.out.println(EvalTest.class.getCanonicalName());
		System.out.println(EvalTest.class.getClass().getResource("/").getPath() + EvalTest.class.getCanonicalName().substring(0, EvalTest.class.getCanonicalName().lastIndexOf(".")).replace(".", "/"));
		
		eval("System.out.println(\"hello world\");");
	}
	
	public static void eval(String codeSnippet){
		execute(codeSnippet);
	}
	
	public static void execute(String codeSnippet){
		CodeBodyFactory factory = new CodeBodyFactory();
		String codeBody = factory.getCodeBody(codeSnippet);
		SimpleRuntime srt = new SimpleRuntime(codeBody);
		try {
			srt.run();
		} catch (Exception e) {
			System.out.println("failed to run code.");
			e.printStackTrace();
		}
	}
}

class CodeBodyFactory{
	private String codeSnippet;
	private String code = 
			this.getClass().getCanonicalName().substring(0, this.getClass().getCanonicalName().lastIndexOf(".")) + ";" +
			"class ObjectProxy{"
			    + "public void invoke() throws Exception{"
			    		+ codeSnippet
				+ "}"
			+ "}";
	
	public String getCodeBody(String codeSnippet){
		this.codeSnippet = codeSnippet;
		return code;
	}
}

class SimpleRuntime extends ClassLoader{
	private String code = "";
	private String path = "";
	private String fileName = "proxy";
	
	public SimpleRuntime(String code){
		this.code = code;
		this.path = this.getClass().getResource("/").getPath() 
				+ this.getClass().getCanonicalName().substring(0, 
						this.getClass().getCanonicalName().lastIndexOf(".")).replace(".", "/");
	}
	
	public void run() throws Exception{
		// 1.compile
		compile();
		
		// 2.loadClass
		Class<?> clazz = this.loadClass(this.getClass().getCanonicalName().substring(0, this.getClass().getCanonicalName().lastIndexOf(".")) + ".ObjectProxy.class");
		
		// 3.invoke method
		Method method = clazz.getMethod("invoke", null);
		method.invoke(null, null);
	}
	
	// compile a java file
	public void compile() throws Exception{
		File javaFile = new File(path + fileName + ".java");
		File classFile = new File(path + fileName + ".class");
		
		if(javaFile.exists()){
			javaFile.delete();;
			javaFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(javaFile);
			fos.write(code.getBytes("UTF-8"));
			fos.close();
		}
		if(classFile.exists()){
			classFile.delete();
		}
		
		Process process = Runtime.getRuntime().exec("javac " + javaFile.getAbsolutePath());
		process.wait();
	}
	
	// read a file
	public byte[] getByte(String file) throws Exception{
		File f = new File(file);
		long length = f.length();
		byte[] b = new byte[(int)length];
		try(FileInputStream fis = new FileInputStream(f)){
			int count = fis.read(b);
			if(count != length){
				throw new IOException("unable to read file content, " + count + "!=" + length);
			}
			return b;
		}
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File classFile = new File(path + fileName + ".class");
		Class<?> clazz = null;
		if(classFile.exists()){
			byte[] b = null;
			try {
				b = getByte(classFile.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			clazz = this.defineClass(name, b, 0, b.length);
		}
		if(clazz == null){
			throw new ClassNotFoundException(name);
		}
		return clazz;
	}
	
}