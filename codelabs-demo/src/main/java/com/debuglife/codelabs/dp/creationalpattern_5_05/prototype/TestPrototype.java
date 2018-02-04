package com.debuglife.codelabs.dp.creationalpattern_5_05.prototype;

/**
 * 原型模式虽然是创建型的模式，但是与工程模式没有关系，从名字即可看出，
 * 该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象
 *
 */
public class TestPrototype {

	public static void main(String[] args) {
	}

    class Prototype implements Cloneable {  
      
        public Object clone() throws CloneNotSupportedException {  
            Prototype proto = (Prototype) super.clone();  
            return proto;  
        }  
    }  

}
