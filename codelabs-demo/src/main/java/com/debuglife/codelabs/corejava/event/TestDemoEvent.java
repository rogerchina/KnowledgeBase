package com.debuglife.codelabs.corejava.event;


public class TestDemoEvent {
    public static void main(String [] args){
        EventSource eventSource = new EventSource();
        FirstEventListener firstEventListener = new FirstEventListener();
        eventSource.addDemoListener(firstEventListener);
        
        SecondEventListener secondEventListener = new SecondEventListener();
        eventSource.addDemoListener(secondEventListener);
        
        eventSource.notifyDemoEvent();
    }
}
