package pt.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateDataConfig {

    @Bean
    // @Transactional
    public FlywayMigrationStrategy repairMigrateStrategy() {
        return flyway -> {
            //flyway.baseline();
            flyway.repair();
            //flyway.validate();
         //   flyway.isIgnoreMissingMigrations();
            flyway.migrate();
        };
    }

}
