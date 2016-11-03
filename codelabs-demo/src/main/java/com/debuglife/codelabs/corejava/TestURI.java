package com.debuglife.codelabs.corejava;

import java.net.URI;


public class TestURI {
    public static void main(String[] args) throws Exception{
        testURI();
    }
    
    public static void testURI() throws Exception{
        String configFile = "xml:D:/workspace-temp/trunk-medavis-dcs/target/dcs-1.0.4.2-SNAPSHOT-bin/dcs-1.0.4.2-SNAPSHOT/conf/sites-configuration.xml";
        URI uri = new URI(configFile);
        System.out.println(uri.getScheme());
        System.out.println(uri.getSchemeSpecificPart());
        System.out.println(uri.getRawUserInfo());
    }
}
