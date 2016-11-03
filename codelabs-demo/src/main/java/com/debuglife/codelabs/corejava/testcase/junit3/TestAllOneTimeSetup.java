package com.debuglife.codelabs.corejava.testcase.junit3;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * 写测试单元
 */
public class TestAllOneTimeSetup extends TestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("TestSuite Test");
		suite.addTestSuite(TestCalcuator.class);
		suite.addTestSuite(TestCalcuator2.class);

		TestSetup wrapper = new TestSetup(suite) {
			protected void setUp() {
				oneTimeSetUp();
			}

			protected void tearDown() {
				oneTimeTearDown();
			}
		};
		return wrapper;
	}

	public static void oneTimeSetUp() {
		// one-time initialization code
	}

	public static void oneTimeTearDown() {
		// one-time cleanup code
	}

	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}
