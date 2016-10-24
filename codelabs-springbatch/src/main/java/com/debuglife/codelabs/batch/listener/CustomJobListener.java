package com.debuglife.codelabs.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * <b>Job 监听器</b><br>
 */
public class CustomJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("before job in job listener...");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("after job in job listener...");
	}

}
