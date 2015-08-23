package com.debuglife.codelabs.crazyit.chapter15;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitorTest {
	public static void main(String[] args) throws Exception{
		Path path = Paths.get("/home/roger");
		Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrjs) throws IOException {
				System.out.println("we are visiting " + file + " file");
				if(file.endsWith("test.txt")){
					System.out.println("---target file found---");
					return FileVisitResult.TERMINATE;
				}
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("we are visiting " + dir);
				return FileVisitResult.CONTINUE;
			}
			
		});
	}
}

class SimpleFileVisitor<T> implements FileVisitor<T>{

	@Override
	public FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileVisitResult visitFile(T file, BasicFileAttributes attrs)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileVisitResult visitFileFailed(T file, IOException exc)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileVisitResult postVisitDirectory(T dir, IOException exc)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}