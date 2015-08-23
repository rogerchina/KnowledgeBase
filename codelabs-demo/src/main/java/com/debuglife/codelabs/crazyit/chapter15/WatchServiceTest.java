package com.debuglife.codelabs.crazyit.chapter15;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceTest {
	public static void main(String[] args) throws Exception{
		WatchService watchService = FileSystems.getDefault().newWatchService();
		Paths.get("/home/roger/test/").register(watchService
				, StandardWatchEventKinds.ENTRY_CREATE
				, StandardWatchEventKinds.ENTRY_DELETE
				, StandardWatchEventKinds.ENTRY_MODIFY);
		while(true){
			WatchKey key = watchService.take();
			for(WatchEvent<?> event : key.pollEvents()){
				System.out.println(event.context() + " file happened " + event.kind() + " event");
			}
			
			boolean valid = key.reset();
			if(!valid){
				break;
			}
		}
	}
}

