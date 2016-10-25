package com.debuglife.codelabs.spring.javacodewiringbean2;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CDPlayer implements MediaPlayer {

	private CompactDisc cd;
	
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}
	
	@Bean
	public CDPlayer cdPlayer() {
		return new CDPlayer(sgtPeppers());
	}
	
	@Bean
	public CDPlayer anotherCDPlayer() {
		return new CDPlayer(sgtPeppers());
	}
	
	@Bean(name="lonelyLovelyHeart")
	public CompactDisc sgtPeppers() {
		return new SgtPeppers();
	}
	
	public void setCompactDisc(CompactDisc cd) {
		this.cd = cd;
	}

	@Bean
	public CDPlayer cdPlayer1(CompactDisc cd) {
		return new CDPlayer(cd);
	}
	
	@Bean
	public CDPlayer cdPlayer2(CompactDisc cd) {
		CDPlayer cdPlayer = new CDPlayer(cd);
		cdPlayer.setCompactDisc(cd);
		return cdPlayer;
	}
	
	@Override
	public void play() {
		cd.play();
	}

}
