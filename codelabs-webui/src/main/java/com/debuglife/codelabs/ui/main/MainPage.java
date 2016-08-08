package com.debuglife.codelabs.ui.main;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * represent as an main page for entry point.
 * 
 */
public class MainPage extends HorizontalLayout {

	private static final long serialVersionUID = -90810153161429144L;
	
	private Accordion accordion;
	
	public MainPage() {
		this.setSizeFull();
		this.setStyleName("main-page");
	}
	
	public void initLayout() {
		accordion = new Accordion();
		accordion.setSizeFull();
		
		for(int i=0; i<10; i++) {
			Label label = new Label("hello", ContentMode.HTML);
			final VerticalLayout layout = new VerticalLayout(label);
			layout.setMargin(true);
			accordion.addTab(layout, "Tab" + i);
		}
 		
		
		addComponent(accordion);
	}
	
}
