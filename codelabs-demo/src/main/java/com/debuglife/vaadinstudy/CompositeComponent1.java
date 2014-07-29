package com.debuglife.vaadinstudy;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class CompositeComponent1 extends CustomComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompositeComponent1(String msg){
		VerticalLayout vlayout = new VerticalLayout();
		// A layout structure used for composition
		Panel panel = new Panel("My custom component");
		panel.setContent(vlayout);
		
		// Compose from multiple components
		Label label = new Label(msg);
		label.setSizeUndefined();
		vlayout.addComponent(label);
		vlayout.addComponent(new Button("OK"));
		
		panel.getContent().setSizeUndefined();
		panel.setSizeUndefined();
		this.setSizeUndefined();
		
		//The composition root MUST be set
		this.setCompositionRoot(panel);
	}
}
