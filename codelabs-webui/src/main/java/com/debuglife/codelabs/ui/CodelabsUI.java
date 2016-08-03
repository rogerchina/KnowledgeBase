package com.debuglife.codelabs.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme(CodelabsTheme.NAME)
@Widgetset(CodelabsWidgetSet.NAME)
public class CodelabsUI extends UI {

	private static final long serialVersionUID = 5315331527479495613L;

	@WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = CodelabsUI.class)
    public static class CodelabsServlet extends VaadinServlet {
		private static final long serialVersionUID = 3960976017691958110L;
    }

    private final VerticalLayout vlayout = new VerticalLayout();
    
    @Override
    protected void init(final VaadinRequest request) {
        try {
            Page.getCurrent().setTitle("BaiduChart");
            vlayout.setStyleName("demoContentLayout");
            vlayout.setMargin(true); 
            vlayout.setSpacing(true);
            
            Label label = new Label("Hello Demo! When you see the information, that means the system is running successfully.");
            vlayout.addComponent(label);
            
            setContent(vlayout);
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
}
