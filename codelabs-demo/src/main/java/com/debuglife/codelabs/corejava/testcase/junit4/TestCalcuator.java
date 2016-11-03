package com.debuglife.codelabs.corejava.testcase.junit4;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;

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
    @Ignore("Not Ready to Run") //对于你想暂时不进行的test cse, 在该方法前添加@Ignore
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCase2(){
        List list = new ArrayList<>();
        Object o = list.get(0);
    }
}
