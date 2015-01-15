package com.debuglife.codelabs.event;

import java.util.ArrayList;
import java.util.List;

public class EventSource {

    private List<DemoEventListener> listeners = new ArrayList<DemoEventListener>();

    public EventSource() {
    }

    public void addDemoListener(DemoEventListener demoListener) {
        listeners.add(demoListener);
    }

    public void notifyDemoEvent() {
        for(DemoEventListener eventListener : listeners) {
            DemoEvent demoEvent = new DemoEvent(this);
            eventListener.processEvent(demoEvent);
        }
    }
}