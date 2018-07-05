package lab.nice.demo.quartz.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

import lab.nice.demo.quartz.common.GuiceQuartzJobFactory;
import lab.nice.demo.quartz.provider.SchedulerProvider;
import lab.nice.demo.quartz.util.PropertiesHandler;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;

import java.util.Properties;

public class QuartzModule extends AbstractModule {

    private String quartzConfig;

    public QuartzModule(String quartzConfig) {
        this.quartzConfig = quartzConfig;
    }

    @Override
    protected void configure() {
        super.configure();
        Properties config = PropertiesHandler.read(quartzConfig);
        bind(Properties.class).annotatedWith(Names.named("schedulerFactoryConfig")).toInstance(config);
        bind(JobFactory.class).to(GuiceQuartzJobFactory.class).in(Scopes.SINGLETON);
        bind(Scheduler.class).toProvider(SchedulerProvider.class).asEagerSingleton();
    }
}
