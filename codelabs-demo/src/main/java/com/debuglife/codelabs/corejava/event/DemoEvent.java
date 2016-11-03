package com.debuglife.codelabs.corejava.event;

import java.util.EventObject;


public class DemoEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public DemoEvent(Object source) {
        super(source);
    }

}
