package hk.ecommerce.controllers;

import hk.ecommerce.entities.AppUser;
import hk.ecommerce.services.AppUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/")
    public List<AppUser> getListUsers(){
        return appUserService.getListUsers();
    }




}
