package lab.nice.demo.quartz.job;

import com.google.inject.Inject;
import lab.nice.demo.quartz.service.ContactService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    @Inject
    private ContactService contactService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("HelloJob is Running. {}", contactService.findById(context.getJobDetail().getJobDataMap().getInt("contact_id")));
    }
}
