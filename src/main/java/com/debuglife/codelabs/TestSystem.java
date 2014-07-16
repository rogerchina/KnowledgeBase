package com.debuglife.codelabs;

import java.util.Map;
import java.util.Properties;

public class TestSystem {
    public static void main(String [] args){
        //testSystemGetProperties();
        //testArrayCopy();
        //testSystemGetEnv();
        //testSystemExit();
        //testIndentityHashCode();
        //testLineSeparator();
        //testLoad();
        //testNanoTime();
        testSetProperties();
    }
    
    static void testSetProperties(){
        Properties props = new Properties();
        props.put("king1", "value1");
        props.put("king2", "value2");
        props.put("king3", "value3");
        
        System.setProperties(props);
        
        System.out.println(System.getProperty("king"));
        System.out.println(System.getProperty("king1"));
    }
    
    static void testNanoTime(){
        System.out.println(System.nanoTime());
    }
    
    static void testLoad(){
        String filePath = "D:\\workspace\\codelabs\\src\\main\\java\\com\\debuglife\\codelabs\\TestSystem.java";// a dynamic lib is required
        System.load(filePath);
        System.loadLibrary(filePath);
    }
    
    static void testLineSeparator(){
        System.out.println(System.lineSeparator() + "jjjj");
        System.out.println("\r\n".equals(System.lineSeparator())); // on Windows
        System.out.println("\n".equals(System.lineSeparator()));  // on Unix
    }
    
    static void testIndentityHashCode(){
        String str1 = "king";
        System.out.println(str1.hashCode());
        System.out.println(System.identityHashCode(str1));
        
        TestSystem ts = new TestSystem();
        System.out.println(ts.hashCode());
        System.out.println(System.identityHashCode(ts));
    }
    
    static void testSystemExit(){
        try {
            Thread.sleep(1000 * 5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        // terminate the current java vitural machine
        System.exit(-1);
        // below sentence will never be executed
        System.out.println("after the call exit() method");
    }
    
    static void testSystemGetProperties(){
        Properties properties = System.getProperties();
        String[] propertiesArray = properties.toString().split(",");
        
        for(String str : propertiesArray)
            System.out.println(str.split("=")[0] + ":" + "\t\t\t\t" + (str.split("=").length == 1? "" : str.split("=")[1]));
        
        System.out.println(System.setProperty("king", "hello"));
        System.out.println(System.setProperty("king", "world"));
        System.out.println(System.getProperty("king"));
        
        System.out.println(System.getProperty("king1"));
        System.out.println(System.getProperty("king1", "haha"));
        
        System.clearProperty("king");
        System.out.println(System.getProperty("king"));
        
        //System.out.println(properties instanceof Map);
    }
    
   static void testArrayCopy(){
        int [] srcArray = {1,2,3,4,5,6};
        int [] desArray = new int[2];
        System.arraycopy(srcArray, 2, desArray, 0, 2);
        for(int i: desArray)
            System.out.println(i);
    }
    
   static void testSystemGetEnv(){
        Map<String, String> envMap = System.getenv();
        for(String key: envMap.keySet()){
            System.out.println(key + ":" + envMap.get(key));
        }
    }
   
   @Override
   public int hashCode(){
       return super.hashCode();
   }
}
