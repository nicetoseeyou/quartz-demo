package lab.nice.demo.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealthCheckJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("HealthCheckJob is Running, dataMap{}", context.getJobDetail().getJobDataMap());
    }

}
