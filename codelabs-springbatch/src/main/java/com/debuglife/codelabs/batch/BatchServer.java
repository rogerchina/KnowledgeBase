package com.debuglife.codelabs.batch;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

/**
 * <b>批处理服务接口</b><br>
 */
public class BatchServer {

	/** 类单例对象 */
	private static final BatchServer INSTANCE = new BatchServer();

	/**
	 * 单例
	 * @return
	 */
	public static BatchServer getInstance() {
		return INSTANCE;
	}

	/**
	 * 私有构造方法
	 */
	private BatchServer() {

	}

	/**
	 * <b>测试作业</b><br>
	 * @param launcher
	 * @param job
	 * @param paraMap
	 */
	public void execCustomJob(JobLauncher launcher, Job job, Map<String, Object> paraMap) {
		JobExecution result = this.executeBatchJob(launcher, job, this.getJobParameters(paraMap));
		System.out.println(result.toString());
	}

	/**
	 * <b>得到作业选项</b><br>
	 * 默认配置任务开始时间
	 * @param paraMap
	 * @return
	 */
	private JobParameters getJobParameters(Map<String, Object> paraMap) {
		HashMap<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		parameters.put("time", new JobParameter(Calendar.getInstance().getTimeInMillis()));
		String key = null;
		Object value = null;

		if (paraMap == null || paraMap.size() == 0) {
			return new JobParameters(parameters);
		}

		for (Entry<String, Object> entry : paraMap.entrySet()) {
			if (entry == null) {
				continue;
			}
			key = entry.getKey();
			value = entry.getValue();

			if (value instanceof Date) {
				parameters.put(key, new JobParameter((Date) value));
			} else if (value instanceof String || value instanceof Integer) {
				parameters.put(key, new JobParameter((String) value));
			} else if (value instanceof Double) {
				parameters.put(key, new JobParameter((Double) value));
			} else if (value instanceof Long) {
				parameters.put(key, new JobParameter((Long) value));
			}
		}

		return new JobParameters(parameters);
	}

	/**
	 * <b>批处理执行器</b><br>
	 * @param joblanuncher
	 * @param job
	 * @param parameters
	 */
	public JobExecution executeBatchJob(JobLauncher launcher, Job job, JobParameters jobParameters) {
		JobExecution result = null;
		try {
			result = launcher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
		}
		return result;
	}
}
