/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.generic;


public class WaterBirdSetting extends BirdSetting{
    private String settingName;
    
    public WaterBirdSetting(String settingName) {
	this.settingName = settingName;
    }
    
    @Override
    public void showSettingName() {
	System.out.println(this.settingName);
    }
}
