package com.debuglife.codelabs.crazyit.chapter15;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public class FileAttributeViewTest {
	public static void main(String[] args) throws Exception{
		Path path = Paths.get("/home/roger/test/test.txt");
		BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
		BasicFileAttributes fileAttributes = view.readAttributes();
		System.out.println(fileAttributes.isDirectory());
		System.out.println(fileAttributes.isRegularFile());
		System.out.println(fileAttributes.isSymbolicLink());
		System.out.println(fileAttributes.lastModifiedTime());
		System.out.println(fileAttributes.size());
		
		File f = new File("/home/roger/test/test.txt");
		System.out.println(f.isDirectory());
		
		FileOwnerAttributeView foaView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
		System.out.println(foaView.getOwner());
		UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("roger");
		foaView.setOwner(user);
		
		UserDefinedFileAttributeView udfaView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		udfaView.write("distribution", Charset.defaultCharset().encode("crazy java alliance"));
		List<String> attrNames = udfaView.list();
		for(String name : attrNames){
			ByteBuffer buffer = ByteBuffer.allocate(udfaView.size(name));
			udfaView.read(name, buffer);
			buffer.flip();
			String value = Charset.defaultCharset().decode(buffer).toString();
			System.out.println(name + "---->" + value);
		}
		
		//linux and unix os don't work
		DosFileAttributeView dfaView = Files.getFileAttributeView(path, DosFileAttributeView.class);
		dfaView.setHidden(true);
		dfaView.setReadOnly(true);
	}
}
