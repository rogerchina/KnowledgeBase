/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs;

public class TestEquals {
    
    public static void main(String[] args) {
	AA a1 = new AA("a");
	AA a2 = new AA("a");
	
	System.out.println(a1.equals(a2));
    }

}

class AA {
    
    private String name;
    
    public AA(String name) {
	this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
	if(this == obj) {
	    return true;
	}

	//public boolean equals(Object obj) {
        //	return (this == obj);
	//}
	if(!super.equals(obj)) { // Error 
	    return false;
	}
	
	return this.name.equals(((AA)obj).name);
    }
}
