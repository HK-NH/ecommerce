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
import java.util.stream.Collectors;

import static hk.ecommerce.util.JwtConstants.*;

@Component
public class JwtUtil {

    private final UserDetailsService userDetailsService;

    private final AppUserService appUserService;

    public JwtUtil(UserDetailsService userDetailsService, AppUserService appUserService) {
        this.userDetailsService = userDetailsService;
        this.appUserService = appUserService;
    }

    public Map<String,String> createJWT(AppUser appUser, HttpServletRequest request){
        final CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(appUser.getUsername());
        Algorithm algorithm =Algorithm.HMAC256(SECRET);
        String jwtAccessToken = JWT.create()
                .withSubject(customUserDetails.getUsername())
                .withIssuer(request.getRequestURL().toString())
                .withExpiresAt(new Date(System.currentTimeMillis()+ EXPIRATIONACCESSTIME))
                .withClaim(CLAIMROLES,customUserDetails.getAuthorities()
                        .stream().map(a -> a.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);
        String jwtRefreshToken = JWT.create()
                .withSubject(customUserDetails.getUsername())
                .withIssuer(request.getRequestURL().toString())
                .withExpiresAt(new Date(System.currentTimeMillis()+ EXPIRATIONREFRESHTIME))
                .sign(algorithm);
        Map<String,String> idToken = new HashMap<>();
        idToken.put("refresh-token",jwtRefreshToken);
        idToken.put("access-token",jwtAccessToken);
        return idToken;
    }

    public Map<String, String> refreshToken(HttpServletRequest request){
        String authToken = request.getHeader(AUTH_HEADER);
        if(authToken!= null && authToken.startsWith(PREFIX)){
            try {
                String refreshToken = authToken.substring(PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
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
