package com.debuglife.codelabs.crazyit.chapter15;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesTest {
	public static void main(String[] args) throws Exception{
		Files.copy(Paths.get("/home/roger/test/test.txt"), new FileOutputStream("/home/roger/test/test1.txt"));
		System.out.println(Files.isHidden(Paths.get("/home/roger/test/test.txt")));
		System.out.println(Files.isHidden(Paths.get("/home/roger/.bashrc")));
		List<String> lines = Files.readAllLines(Paths.get("/home/roger/test/test.txt"), Charset.forName("UTF-8"));
		System.out.println(lines);
		System.out.println(Files.size(Paths.get("/home/roger/test/test.txt")));
		
		List<String> strs = new ArrayList<>();
		strs.add("hello");
		strs.add("world");
		Files.write(Paths.get("/home/roger/test/new.txt"), strs, Charset.forName("UTF-8"));
		
		FileStore fileStore = Files.getFileStore(Paths.get("/"));
		System.out.println(fileStore.getTotalSpace());
		System.out.println(fileStore.getUsableSpace());
		System.out.println(fileStore.getUnallocatedSpace());
	}
}
