package com.debuglife.codelabs.corejava.testcase.junit3;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;


/**
 * 写测试单元
 *
 */
public class TestAll extends TestSuite{
    public static Test suite(){
        TestSuite suite = new TestSuite("TestSuite Test");
        suite.addTestSuite(TestCalcuator.class);
        suite.addTestSuite(TestCalcuator2.class);
        return suite;
    }
    
    public static void main(String[] args){
        TestRunner.run(suite());
    }
}
