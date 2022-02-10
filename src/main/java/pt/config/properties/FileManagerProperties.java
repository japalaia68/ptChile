package pt.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@Getter
@Setter
//@PropertySource("classpath:fileManager.properties")
@ConfigurationProperties(prefix = "file")
public class FileManagerProperties {
    private Map<String, Map<String, String>> s3Conecciones;
}
