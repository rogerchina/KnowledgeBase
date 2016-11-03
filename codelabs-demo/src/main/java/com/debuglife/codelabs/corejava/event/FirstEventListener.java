package com.debuglife.codelabs.corejava.event;


public class FirstEventListener implements DemoEventListener {

    @Override
    public void processEvent(DemoEvent demoEvent) {
        System.out.println("First event listener process event...");
    }

}
