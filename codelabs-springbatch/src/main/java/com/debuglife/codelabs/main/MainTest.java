package com.debuglife.codelabs.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.debuglife.codelabs.batch.BatchServer;


/**
 * <b>批处理测试入口</b><br>
 */
public class MainTest {
	public static void main(String[] args) {
		String dataFileLocation = MainTest.class.getResource("/SpringBatchTest.txt").getFile();
		ApplicationContext context = new FileSystemXmlApplicationContext("src/main/config/spring-application-context.xml");
		testCustomJob(context, dataFileLocation);
	}
	
	private static void testCustomJob(ApplicationContext context, String dataFileLocation) {
		JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("customJob");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("customFileAbPath", dataFileLocation);
		BatchServer.getInstance().execCustomJob(launcher, job, paraMap);
	}
}
