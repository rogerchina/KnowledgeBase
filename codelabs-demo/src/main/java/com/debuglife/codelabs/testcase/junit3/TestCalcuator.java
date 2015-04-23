package com.debuglife.codelabs.testcase.junit3;

import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * 写测试用例
 *
 */
public class TestCalcuator extends TestCase {
    public void testAdd(){
        Calcuator c = new Calcuator();
        double result = c.add(1, 2);
        assertEquals(3, result, 0);
    }
    
    // 测试异常处理
    public void testCase2(){
        ArrayList list = new ArrayList<>();
        try{
            Object o = list.get(0);
        } catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }
    }
}
