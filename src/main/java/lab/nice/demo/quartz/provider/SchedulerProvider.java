package lab.nice.demo.quartz.provider;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;

import java.util.Properties;


public class SchedulerProvider implements Provider<Scheduler> {

    private final Scheduler scheduler;

    @Inject
    public SchedulerProvider(@Named(value = "schedulerFactoryConfig") Properties config) throws SchedulerException {
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        schedulerFactory.initialize(config);
        this.scheduler = schedulerFactory.getScheduler();
    }

    @Inject
    public void setJobFactory(JobFactory jobFactory) throws SchedulerException {
        scheduler.setJobFactory(jobFactory);
    }

    @Override
    public Scheduler get() {
        return scheduler;
    }
}
