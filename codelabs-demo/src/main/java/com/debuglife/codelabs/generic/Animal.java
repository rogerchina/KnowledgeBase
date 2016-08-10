/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.generic;

/**
 * higher level abstraction.
 * The type of animal is restricted to the type or subclass of Bird.
 * So as Bird's setting.
 * 
 * @param <T>
 */
	
public class Animal<T extends Bird< ? extends BirdSetting>> {
    private Bird<BirdSetting> bird;
    
    public Animal(Bird<BirdSetting> bird){
        this.bird = bird;
    }
    
    public void showName() {
	System.out.println(bird.getName());
    }
}
