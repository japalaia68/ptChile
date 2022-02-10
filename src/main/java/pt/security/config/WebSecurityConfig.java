package pt.security.config;

import pt.security.JwtTokenFilterConfigurer;
import pt.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("pt.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${custom.chequeo-seguridad:true}")
    private Boolean chequeoSeguridad;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth) throws Exception {
        authProvider = applicationContext.getBean(CustomAuthenticationProvider.class);
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();
        CorsConfiguration configCors = new CorsConfiguration();
        configCors.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configCors.applyPermitDefaultValues();
        http.cors().configurationSource(request -> configCors);

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.NOT_FOUND));


        // Entry points
        if (!chequeoSeguridad) {
            http.authorizeRequests()//
                    .antMatchers("/**").permitAll()
                    .antMatchers(HttpMethod.PATCH).authenticated()
                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyRequest().authenticated();
        } else {
            http.authorizeRequests()//
                    .antMatchers("/usuario/login").permitAll()
                    .antMatchers("/usuario/captcha_activado").permitAll()
                    //.antMatchers("/pepe").permitAll()
                    .antMatchers(HttpMethod.PATCH).authenticated()
                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyRequest().authenticated();
        }

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/login");


        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/**")
                .antMatchers("/public/**")
                .antMatchers("/apidoc")
                .antMatchers("/actuator/**/**")
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(customPermissionEvaluator);
        web.expressionHandler(handler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return super.authenticationManager();
    }


}
