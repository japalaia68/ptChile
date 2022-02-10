package pt.config.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties
@Getter
@Setter
//@PropertySource(name = "coreProperties", value = "core.properties")
public class CoreProperties {

    @Value("${core.security.jwt.token.expire-length:3600000}")
    private long securityJwtTtokenExpireLength;

    @Value("${core.log.ip-local:127.0.0.1}")
    private String logIpLocal;

    @Value("${core.usuario.max-attempts:3}")
    private Long usuarioMaxAttempts;

    @Value("${core.rol.administrador:ROL_ADMINISTRADOR}")
    private String rolAdministrador;

    @Value("${core.categoria.sinAsignar=:Sin Asignar}")
    private String categoriaPermisoSinAsignar;


}
