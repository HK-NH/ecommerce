package hk.ecommerce.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import hk.ecommerce.entities.AppUser;
import hk.ecommerce.security.CustomUserDetails;
import hk.ecommerce.services.AppUserService;
import hk.ecommerce.vo.LoginForm;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static hk.ecommerce.jwt.JwtConstants.*;

@Component
public class JwtUtil {

    private final UserDetailsService userDetailsService;

    private final AppUserService appUserService;

    private Logger logger = Logger.getLogger(JwtUtil.class.getName());

    public JwtUtil(UserDetailsService userDetailsService, AppUserService appUserService) {
        this.userDetailsService = userDetailsService;
        this.appUserService = appUserService;
    }

    public Map<String,String> createJWT(LoginForm loginForm, HttpServletRequest request){
        final CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loginForm.getUsername());
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
        logger.info("start of refresh token method");
        String authToken = request.getHeader(AUTH_HEADER);
        if(authToken!= null && authToken.startsWith(PREFIX)){
            try {
                String refreshToken = authToken.substring(PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = appUserService.findAppUserByUsername(username);
                LoginForm loginForm = new LoginForm(appUser.getUsername(), appUser.getPassword() );
                return createJWT(loginForm,request);
            } catch (Exception e) {
                throw e;
            }
        }
        else
            throw new RuntimeException("Refresh token required");
    }

}
