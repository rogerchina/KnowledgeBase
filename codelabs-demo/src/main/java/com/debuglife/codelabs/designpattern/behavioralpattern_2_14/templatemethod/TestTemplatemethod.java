package com.debuglife.codelabs.designpattern.behavioralpattern_2_14.templatemethod;

public class TestTemplatemethod {

	public static void main(String[] args) {
		TestTemplatemethod t = new TestTemplatemethod();
		
		String exp = "8+8";  
        AbstractCalculator cal = t.new Plus();  
        int result = cal.calculate(exp, "\\+");  
        System.out.println(result);  

	}

	private abstract class AbstractCalculator {
		/*主方法，实现对本类其它方法的调用*/  
	    public final int calculate(String exp,String opt){  
	        int array[] = split(exp,opt);  
	        return calculate(array[0],array[1]);  
	    }  
	      
	    /*被子类重写的方法*/  
	    abstract public int calculate(int num1,int num2);  
	      
	    public int[] split(String exp,String opt){  
	        String array[] = exp.split(opt);  
	        int arrayInt[] = new int[2];  
	        arrayInt[0] = Integer.parseInt(array[0]);  
	        arrayInt[1] = Integer.parseInt(array[1]);  
	        return arrayInt;  
	    }  
	}
	
	private class Plus extends AbstractCalculator {  
		  
	    @Override  
	    public int calculate(int num1,int num2) {  
	        return num1 + num2;  
	    }  
	}  
}
