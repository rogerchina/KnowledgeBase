package com.debuglife.vaadinstudy.echart;


public enum MyComponentChartShape {
    LINE("line"),
    BAR("bar"),
    SCATTER("scatter"),
    PIE("pie"),
    RADAR("radar");
    
    private final String name;
    private MyComponentChartShape(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
