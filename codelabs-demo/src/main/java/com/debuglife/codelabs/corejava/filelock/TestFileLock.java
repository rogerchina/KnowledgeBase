package com.debuglife.codelabs.corejava.filelock;


public class TestFileLock {
    public static void main(String[] args){
        TestThreadWriteFile writeFile = new TestThreadWriteFile();
        TestThreadReadFile readFile = new TestThreadReadFile();
        
        writeFile.start();
        readFile.start();
    }
}
