package com.debuglife.codelabs.appmanage;

/**
 * object which is interactiving with start/stop script
 * @author roger
 *
 */
public class DCSBootLoader {
    
    private static DCSBootLoader daemon = null;
    private static DCS dcsService = null;
    
    private void init(String[] args) throws Exception{
        // do something
        // config
        // init service
        dcsService = new DCS();
        dcsService.loadArgs(args);
    }
    
    public static void main(String[] args) {
        if(daemon == null){
            DCSBootLoader bootLoader = new DCSBootLoader();
            try {
                // init dcs service
                bootLoader.init(args);
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
            dcsService.startup();
            dcsService.start();
        }
        
        if(command.equals("stop")){
            if(dcsService.isRunning()){
                dcsService.shutdown();
            }
        }
    }
}
