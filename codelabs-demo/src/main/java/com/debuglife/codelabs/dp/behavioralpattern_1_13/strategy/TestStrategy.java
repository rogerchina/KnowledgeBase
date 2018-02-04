package com.debuglife.codelabs.dp.behavioralpattern_1_13.strategy;

/**
 * 策略模式的决定权在用户，系统本身提供不同算法的实现，新增或者删除算法，对各种算法做封装。
 * 因此，策略模式多用在算法决策系统中，外部用户只需要决定用哪个算法即可。
 * @author roger.zhang
 *
 */
public class TestStrategy {

	public static void main(String[] args) {
		TestStrategy t = new TestStrategy();

		String exp = "2+8";
		ICalculator cal = t.new Plus();
		int result = cal.calculate(exp);
		System.out.println(result);
	}

	private interface ICalculator {
		public int calculate(String exp);
	}

	private abstract class AbstractCalculator {

		public int[] split(String exp, String opt) {
			String array[] = exp.split(opt);
			int arrayInt[] = new int[2];
			arrayInt[0] = Integer.parseInt(array[0]);
			arrayInt[1] = Integer.parseInt(array[1]);
			return arrayInt;
		}
	}

	// 3 implementation classes

	private class Plus extends AbstractCalculator implements ICalculator {

		@Override
		public int calculate(String exp) {
			int arrayInt[] = split(exp, "\\+");
			return arrayInt[0] + arrayInt[1];
		}
	}

	private class Minus extends AbstractCalculator implements ICalculator {

		@Override
		public int calculate(String exp) {
			int arrayInt[] = split(exp, "-");
			return arrayInt[0] - arrayInt[1];
		}

	}

	private class Multiply extends AbstractCalculator implements ICalculator {

		@Override
		public int calculate(String exp) {
			int arrayInt[] = split(exp, "\\*");
			return arrayInt[0] * arrayInt[1];
		}
	}
}
