/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.fileIO;

import java.io.File;
import java.io.FileWriter;


public class CopyString {
    public static void main(String[] args) throws Exception{
        File f = new File("c:\\temp\\string.txt");
        if(!f.exists()) {
            f.createNewFile();
        }
        
        FileWriter fw = new FileWriter(f);
        fw.write(copy());
        fw.flush();
        fw.close();
    }
    
    private static String copy() {
        StringBuffer sb = new StringBuffer();
        
        String frameCount = "frameCount";
        String id = "id";
        String instanceNumber = "instanceNumber";
        String sopInstanceUid = "sopInstanceUid";
        String testurl = "testurl";
        
        for(int i=0; i<1000; i++) {
            sb.append("{\r\n");
            
            sb.append("\t" + frameCount + " : 0,\r\n");
            sb.append("\t" + id + " : " + (111 + i) + ",\r\n");
            sb.append("\t" + instanceNumber + " : " + (11 + i) + ",\r\n");
            sb.append("\t" + sopInstanceUid + " : \"1.2.276.0.50.172016064021.15359972.1871240." + (45 + i) + "\",\r\n");
            sb.append("\t" + testurl + " : \"demo_images/IM0000_" + i + ".JPG\"\r\n");
            
            sb.append("},\r\n");
        }
        
        return sb.toString();
    }
}
