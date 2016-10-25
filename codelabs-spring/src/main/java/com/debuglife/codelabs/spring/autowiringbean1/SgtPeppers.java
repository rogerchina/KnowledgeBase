package com.debuglife.codelabs.spring.autowiringbean1;

import org.springframework.stereotype.Component;

@Component("LonelyClubSgtPeppers")
public class SgtPeppers implements CompactDisc {
	
	private String title = "MyLovelyHeart";
	private String article = "Chen rui";
	
	@Override
	public void play() {
		System.out.println("Play " + title + " by " + article);
	}

}
