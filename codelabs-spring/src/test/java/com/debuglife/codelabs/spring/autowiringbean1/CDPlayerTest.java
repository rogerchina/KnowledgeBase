package com.debuglife.codelabs.spring.autowiringbean1;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.debuglife.codelabs.spring.autowiringbean1.CDPlayerConfig;
import com.debuglife.codelabs.spring.autowiringbean1.CompactDisc;
import com.debuglife.codelabs.spring.autowiringbean1.MediaPlayer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {
	
	@Rule
	public final SystemOutRule log = new SystemOutRule();

	@Autowired
	private CompactDisc cd;

	@Autowired
	private MediaPlayer mp;
	
	@Test
	public void cdShouldNotBeNull() {
		assertNotNull(cd);
	}
	
	@Test
	public void play() {
		mp.play();
		assertEquals("Play MyLovelyHeart by Chen rui", log.getLog());
	}
}
