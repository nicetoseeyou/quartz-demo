package lab.nice.demo.quartz;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lab.nice.demo.quartz.job.HealthCheckJob;
import lab.nice.demo.quartz.job.HelloJob;
import lab.nice.demo.quartz.module.DataSourceModule;
import lab.nice.demo.quartz.module.QuartzModule;
import lab.nice.demo.quartz.module.ServiceModule;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


public class App {
    public static void main(String[] args) {
        Injector init = Guice.createInjector(
                new DataSourceModule("E:\\git\\quartz-demo\\src\\main\\resources\\mysql.config.properties"),
                new ServiceModule(),
                new QuartzModule("E:\\git\\quartz-demo\\src\\main\\resources\\quartz.properties"));

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = init.getInstance(Scheduler.class);
            // and start it off
            JobDetail job = newJob()
                    .ofType(HelloJob.class) //引用Job Class
                    .withIdentity("job1", "group1") //设置name/group
                    .withDescription("this is a test job") //设置描述
                    .usingJobData("contact_id", 1) //加入属性到ageJobDataMap
                    .build();

            CronTrigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(cronSchedule("0/20 * * * * ?"))
                    .build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
            Thread.sleep(60L * 1000L);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
