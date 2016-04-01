/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.generic;


public class Animal<T extends Bird> {
    private Bird bird;
    
    public Animal(Bird bird){
        this.bird = bird;
    }
    
    public void fly(){
        bird.fly();
    }
}
