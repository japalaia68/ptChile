package pt.security;

import pt.config.RestResponseExceptionHandler;
import pt.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import murraco.exception.CustomException;
@EnableCaching
public class JwtTokenFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;


    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);

        try {
            Authentication auth;
            if (token != null && jwtTokenProvider.validateToken(token)) {
                auth = jwtTokenProvider.getAuthentication(token);

                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        } catch (Exception e) {
            ResponseEntity responseEntity = null;
            if (e instanceof CustomException) {
                RestResponseExceptionHandler restResponseExceptionHandler = new RestResponseExceptionHandler();
                responseEntity = restResponseExceptionHandler.customExceptions((CustomException) e);
            }

            byte[] responseToSend = restResponseBytes(responseEntity.getBody());
            ((HttpServletResponse) res).setHeader("Content-Type", "application/json");
            ((HttpServletResponse) res).setStatus(responseEntity.getStatusCodeValue());
            res.getOutputStream().write(responseToSend);
            return;
        }

        filterChain.doFilter(req, res);
    }


    private byte[] restResponseBytes(Object object) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(object);
        return serialized.getBytes();
    }
}
