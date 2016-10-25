package com.debuglife.codelabs.spring.autowiringbean1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CDPlayer implements MediaPlayer {

	private CompactDisc cd;
	
	@Autowired
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}
	
	@Autowired
	public void setCompactDisc(CompactDisc cd) {
		this.cd = cd;
	}
	
	@Autowired(required=false)
	public void insertCompactDisc(CompactDisc cd) {
		this.cd = cd;
	}
	
	@Override
	public void play() {
		cd.play();
	}

}
