package pt.config.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha")
@Getter
@Setter
public class CaptchaSettings {

    private String site;
    private String secret;
    private String endpoint;
    private Boolean serverSinInternetOk;
    private Boolean validaCaptcha;
    private String usuarioSinCapcha;
}
