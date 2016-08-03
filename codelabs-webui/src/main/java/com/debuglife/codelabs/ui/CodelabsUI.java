/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme(CodelabsTheme.NAME)
@Widgetset(CodelabsWidgetSet.NAME)
public class CodelabsUI extends UI {

	private static final long serialVersionUID = 5315331527479495613L;

	@WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = CodelabsUI.class)
    public static class Servlet extends VaadinServlet {

		private static final long serialVersionUID = 3960976017691958110L;
    }

    // Show it in the middle of the screen
    //private final VerticalLayout vlayout = new VerticalLayout();
    // Chart component
    //private BaiduChart baiduChart;
    
    @Override
    protected void init(final VaadinRequest request) {
    	/*
        try {
			
            // Set the page title (window or tab caption)
            Page.getCurrent().setTitle("BaiduChart");
            // Set the root panel
            vlayout.setStyleName("demoContentLayout");
            // Enable layout margins. Affects all four sides of the layout
            vlayout.setMargin(true); 
            vlayout.setSpacing(true);
            
            final ComboBox comboxWorkflow = new ComboBox("Please select workflow:", Arrays.asList(WorkflowDummy.values()));
            comboxWorkflow.setTextInputAllowed(false);
            comboxWorkflow.setNewItemsAllowed(false);
            comboxWorkflow.setNullSelectionAllowed(false);
            comboxWorkflow.setRequired(true);
            comboxWorkflow.setRequiredError("should choose one item!");
            comboxWorkflow.setImmediate(true);
            comboxWorkflow.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        final WorkflowDummy workflow = (WorkflowDummy)event.getProperty().getValue();
                        Notification.show(workflow.getName());
                        vlayout.removeComponent(baiduChart);
                        if(workflow != null){
                            if(workflow.getName().equals("ris_his")){
                                showChart("ris_his");
                            }else if(workflow.getName().equals("pacs_g4m")){
                                showChart("pacs_g4m");
                            }else if(workflow.getName().equals("relabit_pabs_refer")){
                                showChart("relabit_pabs_refer");
                            }
                        }
                    } catch(Exception e) {
                        //do nothing
                    }
                    
                }
            });
            comboxWorkflow.setValue("ris_his");
            vlayout.addComponent(comboxWorkflow);
            
            final ComboBox comboxTheme = new ComboBox("Please select theme:", Arrays.asList(BaiduChartTheme.values()));
            comboxTheme.setImmediate(true);
            comboxTheme.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    final BaiduChartTheme theme = (BaiduChartTheme)event.getProperty().getValue();
                    Notification.show(theme.getName());
                    //TODO
                    baiduChart.setTheme(theme);
                }
            });
            vlayout.addComponent(comboxTheme);
            
            // display by default
            showChart("ris_his");
            //showChart("ris_his");
            
            setContent(vlayout);
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
		*/
    }
}
