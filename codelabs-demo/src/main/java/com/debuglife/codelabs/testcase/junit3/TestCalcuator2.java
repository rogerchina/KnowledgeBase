package com.debuglife.codelabs.testcase.junit3;

import junit.framework.TestCase;

/**
 * 写测试用例
 *
 */
public class TestCalcuator2 extends TestCase {
    public void testAdd(){
        Calcuator c = new Calcuator();
        double result = c.add(1, 2);
        assertEquals(3, result, 0);
    }
}
