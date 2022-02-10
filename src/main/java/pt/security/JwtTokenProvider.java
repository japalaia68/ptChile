package pt.security;

import pt.config.core.CoreProperties;
import pt.exception.CustomException;
import pt.exception.CustomTokenInvalidoException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

// import murraco.exception.CustomException;
// import murraco.model.Role;

@Component
public class JwtTokenProvider {

    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
     * microservices environment, this key would be kept on a config-server.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;



    @Autowired
    private MyUserDetails myUserDetails;

    @Autowired
    private CoreProperties coreProperties;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username,List<String> roles /*List<Rol> roles*/) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth","rol" /*roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList())*/);

        Date now = new Date();
        Date validity = new Date(now.getTime() + coreProperties.getSecurityJwtTtokenExpireLength());

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    public Authentication getAuthentication(String token) throws UsernameNotFoundException {
        Authentication authentication = null;
        try {
            UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));

            authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

        } catch (UsernameNotFoundException e) {
            throw e;
        }
        return authentication;
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) throws CustomException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomTokenInvalidoException();
        }
    }

}
