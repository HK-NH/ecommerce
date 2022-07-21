package hk.ecommerce;

import hk.ecommerce.entities.AppRole;
import hk.ecommerce.entities.AppUser;
import hk.ecommerce.services.AppRoleService;
import hk.ecommerce.services.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AppRoleService appRoleService, AppUserService appUserService){
        return args -> {
            AppRole appRole = new AppRole(null,"ADMIN");
            appRoleService.addRole(appRole);
            AppRole appRole2 = new AppRole(null,"REGULAR");
            appRoleService.addRole(appRole2);
            AppUser appUser = new AppUser(null,"firstName","lastName","0123456","adress","admin","123", List.of(appRole));
            appUser.setActive(true);
            appUserService.saveUser(appUser);
            AppUser appUser2 = new AppUser(null,"firstName2","lastName2","0123456","adress2","test","123", null);
            appUserService.saveUser(appUser2);
            appUserService.addRoleToUser("test","REGULAR");
        };
    }

}
