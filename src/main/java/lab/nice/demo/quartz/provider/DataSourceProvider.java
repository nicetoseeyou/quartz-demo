package lab.nice.demo.quartz.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DataSourceProvider implements Provider<HikariDataSource> {
    private HikariConfig config;

    @Inject
    public DataSourceProvider(HikariConfig config) {
        this.config = config;
    }

    @Override
    public HikariDataSource get() {
        final HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
