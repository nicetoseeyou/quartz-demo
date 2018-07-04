package lab.nice.demo.quartz.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesHandler {


    public static Properties read(String file) {
        Properties properties = new Properties();
        Path path = Paths.get(file);
        try (InputStream inputStream = Files.newInputStream(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
