package com.debuglife.codelabs.crazyit.chapter18;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class CompileClassLoaderTest extends ClassLoader {

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
	
	// compile java file
	public boolean compile(String javaFile) throws Exception{
		System.out.println("System is compiling " + javaFile + " ...");
		Process p = Runtime.getRuntime().exec("javac " + javaFile);
		p.waitFor();
		int returnValue = p.exitValue();
		return returnValue == 0;
	}
	
	//override the findClass(...) method in Parent ClassLoader
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> clazz = null;
		String fileStub = name.replace(".", "/");
		String javaFileName = fileStub + ".java";
		String classFileName = fileStub + ".class";
		File javaFile = new File(javaFileName);
		File classFile = new File(classFileName);
		if(javaFile.exists() &&((!classFile.exists()) || javaFile.lastModified() > classFile.lastModified())){
			try {
				if(!compile(javaFileName) || !classFile.exists()){
					throw new ClassNotFoundException("ClassNotFoundException: " + javaFileName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(classFile.exists()){
			try {
				byte[] raw = getByte(classFileName);
				clazz = this.defineClass(name, raw, 0, raw.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(clazz == null){
			throw new ClassNotFoundException(name);
		}
		return clazz;
	}
	
	public static void main(String[] args) throws Exception{
		if(args.length < 1){
			System.out.println("缺少目标类，请按照如下格式运行java源文件.");
			System.out.println("java CompileClassLoader Classname");
			System.exit(0);
		}
		
		String className = args[0];
		String[] progArgs = new String[args.length-1];
		System.arraycopy(args, 1, progArgs, 0, progArgs.length);
		CompileClassLoaderTest cclt = new CompileClassLoaderTest();
		Class<?> clazz = cclt.loadClass(className);
		Method main = clazz.getMethod("main", (new String[0]).getClass());
		Object argsArray[] = {progArgs};
		main.invoke(null, argsArray);
	}
}
