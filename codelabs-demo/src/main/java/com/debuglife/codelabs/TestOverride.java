package com.debuglife.codelabs;


public class TestOverride {
    public static void main(String[] args){
        B b = new B();
        b.test();
        
        C c = new D();
        c.test();
    }
}

class A{
    public void test(){
        System.out.println("parent");
    }
}

class B extends A{
    @Override
    public synchronized void test(){
        System.out.println("child");
    }
}

interface C{
    void test();
}

class D implements C{

    @Override
    public synchronized void test() {
        System.out.println("d");
    }
    
}