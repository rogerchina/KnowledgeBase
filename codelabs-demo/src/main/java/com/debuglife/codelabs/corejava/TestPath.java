/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.corejava;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestPath {
    public static void main(String[] args) {
	Path p = Paths.get("c://test");
	Path p1 = p.resolve("test1//hello.txt");
	Path p2 = Paths.get("c://test", "test1");
	
	
	System.out.println(p);
	System.out.println(p1);
	System.out.println(p2);
	
    }
}
