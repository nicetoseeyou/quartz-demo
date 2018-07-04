package lab.nice.demo.quartz;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lab.nice.demo.quartz.module.DataSourceModule;
import lab.nice.demo.quartz.provider.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {
        Injector init = Guice.createInjector(new DataSourceModule("E:\\git\\quartz-demo\\src\\main\\resources\\mysql.config.properties"));
        Connection connection =  init.getInstance(ConnectionProvider.class).get();
        PreparedStatement statement = connection.prepareStatement("select * from contacts");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt("contact_id"));
            System.out.println(resultSet.getString("last_name"));
            System.out.println(resultSet.getString("first_name"));
            System.out.println(resultSet.getDate("birthday"));
        }
        resultSet.close();
        statement.close();
    }
}
