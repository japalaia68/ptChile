package pt.security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component()
public class CustomAuthenticationProvider
        implements AuthenticationProvider {



    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
                return new UsernamePasswordAuthenticationToken(
                        name, password, new ArrayList<>());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Error inesperado en authenticate ", e);
        } catch (Exception e) {
            throw new RuntimeException("Error en la comunicacion con el AD: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}

