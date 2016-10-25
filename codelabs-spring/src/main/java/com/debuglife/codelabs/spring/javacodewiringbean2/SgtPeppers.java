package com.debuglife.codelabs.spring.javacodewiringbean2;


public class SgtPeppers implements CompactDisc {
	
	private String title = "MyLovelyHeart";
	private String article = "Chen rui";
	
	@Override
	public void play() {
		System.out.println("Play " + title + " by " + article);
	}

}
