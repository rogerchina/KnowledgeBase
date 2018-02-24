/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.crazyit.chapter6;

public class LambdaTest {
    public static void main(String[] args) {
		ProcessArray pa = new ProcessArray();
		int[] arr = {3, -4, 6, 4};
		pa.process(arr, new CommandTemplate(){

			@Override
			public void process(int[] arr) {
			int sum = 0;
			for(int tmp : arr) {
				sum += tmp;
			}
			System.out.println("sum=" + sum);
			}

		});


		pa.process(arr, (int[] target)->{
			int sum = 0;
			for(int tmp : target) {
			sum += tmp;
			}
			System.out.println(sum);
		});
    }
}

class ProcessArray {
    public void process(int[] arr, CommandTemplate ct) {
	ct.process(arr);
    }
}

interface CommandTemplate {
    void process(int[] arr);
}
