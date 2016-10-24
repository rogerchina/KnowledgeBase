package com.debuglife.codelabs.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * <b>Step 监听器</b><br>
 */
public class CustomStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("before step in step listener...");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("after step in step listener...");
		return ExitStatus.COMPLETED;
	}

}
