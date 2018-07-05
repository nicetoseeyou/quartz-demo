package lab.nice.demo.quartz.module;

import com.google.inject.AbstractModule;
import lab.nice.demo.quartz.service.ContactService;
import lab.nice.demo.quartz.service.impl.ContactServiceImpl;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();
        bind(ContactService.class).to(ContactServiceImpl.class);
    }
}
