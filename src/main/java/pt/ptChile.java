package pt;

import pt.config.properties.FileManagerProperties;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = {"pt"})
@EnableConfigurationProperties({FileManagerProperties.class})
@Configuration
public class ptChile extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ptChile.class);
    private static String location;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(ptChile.class, args);

        LOGGER.info("Application running...");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
