package pt.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetails implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetails.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     //   Usuario user;
        String password="";
        try {
//            user = usuarioService.getUsuarioByUsuario(username, true);
//            if (user.getPassword() == null) {
//                password = "";
//            } else {
//                password = user.getPassword();
//            }

//            if (user == null)
//                throw new UsernameNotFoundException("El usuario username, no existe");
            return org.springframework.security.core.userdetails.User//
                    .withUsername(username)//
                    .password(password)//
                    .authorities("user.getRoles()")//
                    .accountExpired(false)//
                    .accountLocked(false)//
                    .credentialsExpired(false)//
                    .disabled(false)//
                    .build();
        } catch (Exception e) {
            LOGGER.error("Error en loadUserByUsername:" + e.getMessage());
        }
        return null;
    }

}
