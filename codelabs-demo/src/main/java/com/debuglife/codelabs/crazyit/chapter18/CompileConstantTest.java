package com.debuglife.codelabs.crazyit.chapter18;

public class CompileConstantTest {
    public static void main(String[] args) {
	System.out.println(MyTest.compileConstant);
	System.out.println(MyTestParent.myTestConstant);
    }
}

class MyTest extends MyTestParent {
    static {
	System.out.println("静态初始化块...");
    }
    static final String compileConstant = System.currentTimeMillis() + "疯狂java讲义";
}

class MyTestParent {
    static {
	System.out.println("MyTestParent 静态初始化...");
    }
    static final String myTestConstant = System.currentTimeMillis() + "MyTestParent";
}
