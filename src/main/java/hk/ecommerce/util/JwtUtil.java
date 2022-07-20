package hk.ecommerce.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import hk.ecommerce.entities.AppUser;
import hk.ecommerce.security.CustomUserDetails;
import hk.ecommerce.services.AppUserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final String keySecret = "HamzaKecha";

    private static final int expirationAccessTime = 36000*6;

    private static final int expirationRefreshTime = 24*3600*1000;

    private final UserDetailsService userDetailsService;

    private final AppUserService appUserService;

    private Logger logger = Logger.getLogger(JwtUtil.class.getName());

    public JwtUtil(UserDetailsService userDetailsService, AppUserService appUserService) {
        this.userDetailsService = userDetailsService;
        this.appUserService = appUserService;
    }

    public Map<String,String> createJWT(AppUser appUser, HttpServletRequest request){
        logger.info(String.valueOf(expirationRefreshTime));
        final CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(appUser.getUsername());
        Algorithm algorithm =Algorithm.HMAC256(keySecret);
        String jwtAccessToken = JWT.create()
                .withSubject(customUserDetails.getUsername())
                .withIssuer(request.getRequestURL().toString())
                .withExpiresAt(new Date(System.currentTimeMillis()+expirationAccessTime))
                .withClaim("roles",customUserDetails.getAuthorities()
                        .stream().map(a -> a.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);
        String jwtRefreshToken = JWT.create()
                .withSubject(customUserDetails.getUsername())
                .withIssuer(request.getRequestURL().toString())
                .withExpiresAt(new Date(System.currentTimeMillis()+expirationRefreshTime))
                .sign(algorithm);
        Map<String,String> idToken = new HashMap<>();
        idToken.put("refresh-token",jwtRefreshToken);
        idToken.put("access-token",jwtAccessToken);
        return idToken;
    }

    public Map<String, String> refreshToken(HttpServletRequest request){
        String authToken = request.getHeader("Authorization");
        if(authToken!= null && authToken.startsWith("Bearer ")){
            try {
                String refreshToken = authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(keySecret);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = appUserService.findAppUserByUsername(username);
                return createJWT(appUser,request);
            } catch (Exception e) {
                throw e;
            }
        }
        else
            throw new RuntimeException("Refresh token required");
    }
}
