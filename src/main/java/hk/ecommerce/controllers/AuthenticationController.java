package hk.ecommerce.controllers;

import hk.ecommerce.entities.AppUser;
import hk.ecommerce.services.AppUserService;
import hk.ecommerce.services.VerificationTokenService;
import hk.ecommerce.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final VerificationTokenService verificationTokenService;

    public AuthenticationController(JwtUtil jwtUtil, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, AppUserService appUserService, VerificationTokenService verificationTokenService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
        this.verificationTokenService = verificationTokenService;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody AppUser appUser, HttpServletRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),appUser.getPassword()));
        return jwtUtil.createJWT(appUser,request);
    }

    @PostMapping("/register")
    public void register(@RequestBody AppUser appUser){
        appUserService.saveUser(appUser);
        verificationTokenService.generateToken(appUser);
    }

    @GetMapping("/validateRegistration/{token}")
    public void validate(@PathVariable String token){
        appUserService.validateUser(token);
    }

    @GetMapping("/refreshToken")
    public Map<String, String> refreshToken(HttpServletRequest request){
        return jwtUtil.refreshToken(request);
    }

}
