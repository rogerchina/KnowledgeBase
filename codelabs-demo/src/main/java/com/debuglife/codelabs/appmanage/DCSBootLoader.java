package com.debuglife.codelabs.appmanage;

/**
 * object which is interactiving with start/stop script
 * @author roger
 *
 */
public class DCSBootLoader {
    
    private static DCSBootLoader daemon = null;
    
    private void init() throws Exception{
        // do something
    }
    
    public static void main(String[] args) {
        if(daemon == null){
            DCSBootLoader bootLoader = new DCSBootLoader();
            try {
                bootLoader.init();
            } catch(Exception e) {
                e.printStackTrace();
                return;
            }
            daemon = bootLoader;
        }else{
            System.out.println("started!");
            return;
        }
        
        String command = "start";
        if(args.length > 0){
            command = args[args.length - 1];
        }
        
        if(command.equals("start")){
            //startUpDCS(args);
            TestStartUpWithBatCommond.start(args);
        }
        
        if(command.equals("stop")){
            //shutdownDCS(args)
            TestShutdownWithBatCommond.stop(args);
        }
    }
}
