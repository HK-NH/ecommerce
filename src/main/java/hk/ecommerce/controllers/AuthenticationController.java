package hk.ecommerce.controllers;

import hk.ecommerce.amqp.RabbitSender;
import hk.ecommerce.entities.AppUser;
import hk.ecommerce.services.AppUserService;
import hk.ecommerce.services.RegistrationTokenService;
import hk.ecommerce.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final RegistrationTokenService verificationTokenService;
    private final RabbitSender rabbitSender;

    public AuthenticationController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, AppUserService appUserService, RegistrationTokenService verificationTokenService, RabbitSender rabbitSender) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
        this.verificationTokenService = verificationTokenService;
        this.rabbitSender = rabbitSender;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody AppUser appUser, HttpServletRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),appUser.getPassword()));
        return jwtUtil.createJWT(appUser,request);
    }

//    @PostMapping("/register")
//    public void register(@RequestBody AppUser appUser){
//        appUserService.saveUser(appUser);
//        String token = verificationTokenService.generateToken(appUser);
//        rabbitSender.send(token);
//    }

    @GetMapping("/validateRegistration/{token}")
    public Boolean validate(@PathVariable String token){
        return appUserService.validateUser(token);
        // send email that account has been confirmed
    }

    @GetMapping("/refreshToken")
    public Map<String, String> refreshToken(HttpServletRequest request){
        return jwtUtil.refreshToken(request);
    }

}
