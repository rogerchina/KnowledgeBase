package com.debuglife.codelabs.event;

import java.util.EventListener;


public interface DemoEventListener extends EventListener {
    public void processEvent(DemoEvent demoEvent);
}
