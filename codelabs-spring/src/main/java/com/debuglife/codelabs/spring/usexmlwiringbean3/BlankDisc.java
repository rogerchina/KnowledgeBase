package com.debuglife.codelabs.spring.usexmlwiringbean3;

import java.util.List;

public class BlankDisc implements CompactDisc {
	
	private String title;
	private String article;
	private List<String> tracks;
	
	public BlankDisc(String title, String article, List<String> tracks) {
		this.title = title;
		this.article = article;
		this.tracks = tracks;
	}
	
	@Override
	public void play() {
		System.out.println("Playing " + title + " by " + article);
		for(String track : tracks) {
			System.out.println("-Track: " + track);
		}
	}

}
