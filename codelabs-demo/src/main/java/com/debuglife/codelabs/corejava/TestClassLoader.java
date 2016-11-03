package com.debuglife.codelabs.corejava;


public class TestClassLoader {
    public static void main(String[] args){
        
    }
}

class NetWorkClassLoader extends ClassLoader{
    String host;
    int port;

    public NetWorkClassLoader(String host, int port){
        this.host = host;
        this.port = port;
    }
    
    public Class findClass(String name) {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        // load the class data from the connection
        return null;
    }
}
