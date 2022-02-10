package pt.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom.roles.custom")
@Getter
@Setter
public class RolEvaluacionSettings {

    private Long rolAdministrador;

}
