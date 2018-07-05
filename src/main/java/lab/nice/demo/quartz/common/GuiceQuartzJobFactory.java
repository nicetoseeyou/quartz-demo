package lab.nice.demo.quartz.common;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuiceQuartzJobFactory implements JobFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuiceQuartzJobFactory.class);

    private final Injector guice;
    @Inject
    public GuiceQuartzJobFactory(Injector guice) {
        this.guice = guice;
    }

    @Override
    public Job newJob(TriggerFiredBundle triggerFiredBundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail =triggerFiredBundle.getJobDetail();
        Class<? extends Job> jobClass= jobDetail.getJobClass();
        return guice.getInstance(jobClass);
    }

}
