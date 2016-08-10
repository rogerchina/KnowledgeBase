/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.generic;


public abstract class Bird<T extends BirdSetting> {
    private T setting;
    private String name;
    
    public abstract void fly(); 
    
    public T getSetting() {
	return setting;
    }
    
    public String getName() {
	return name;
    }
}
