package com.debuglife.codelabs.addon;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({"http://s1.bdstatic.com/r/www/cache/ecom/esl/1-6-10/esl.js","VChartAddOn.js"})
public class VChartAddOn extends AbstractJavaScriptComponent {

    private static final long serialVersionUID = -6327032575972743645L;
    
    public VChartAddOn(){
        super();
    }

}
