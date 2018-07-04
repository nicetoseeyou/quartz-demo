package lab.nice.demo.quartz.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lab.nice.demo.quartz.provider.ConnectionProvider;
import lab.nice.demo.quartz.provider.DataSourceProvider;
import lab.nice.demo.quartz.util.PropertiesHandler;

import java.sql.Connection;
import java.util.Properties;

public class DataSourceModule extends AbstractModule {

    private String path;

    public DataSourceModule(String path) {
        this.path = path;
    }

    @Override
    protected void configure() {
        super.configure();
        Properties properties = PropertiesHandler.read(path);
        HikariConfig config = new HikariConfig(properties);
        bind(HikariConfig.class).toInstance(config);
        bind(HikariDataSource.class).toProvider(DataSourceProvider.class).in(Scopes.SINGLETON);
        bind(Connection.class).toProvider(ConnectionProvider.class);
    }
}
