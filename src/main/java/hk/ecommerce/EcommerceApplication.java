package hk.ecommerce;

import hk.ecommerce.entities.AppRole;
import hk.ecommerce.entities.AppUser;
import hk.ecommerce.entities.Category;
import hk.ecommerce.entities.Product;
import hk.ecommerce.services.AppRoleService;
import hk.ecommerce.services.AppUserService;
import hk.ecommerce.services.CategoryService;
import hk.ecommerce.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AppRoleService appRoleService, AppUserService appUserService, CategoryService categoryService, ProductService productService){
        return args -> {
            AppRole appRole = new AppRole(null,"ADMIN");
            appRoleService.save(appRole);
            AppRole appRole2 = new AppRole(null,"REGULAR");
            appRoleService.save(appRole2);
            AppUser appUser = new AppUser(null,"firstName","lastName","0123456","adress","admin","123", List.of(appRole));
            appUser.setActive(true);
            appUserService.saveUser(appUser);
            AppUser appUser2 = new AppUser(null,"firstName2","lastName2","0123456","adress2","test","123", null);
            appUserService.saveUser(appUser2);
            appUserService.addRoleToUser("test","REGULAR");
            // ===============================================================================================
            Category c = new Category(null,"Electronics",null);
            categoryService.save(c);
            Category c1 = new Category(null,"Food",null);
            categoryService.save(c1);

            Product phone = new Product(null,"Phone","a description", BigDecimal.valueOf(50.0),100,null,c);
            productService.save(phone);
            Product tv = new Product(null,"TV","a description", BigDecimal.valueOf(50.0),100,null,c);
            productService.save(tv);
            Product laptop = new Product(null,"Laptop","a description", BigDecimal.valueOf(50.0),100,null,c);
            productService.save(laptop);
            Product pizza = new Product(null,"Pizza","a description of a pizza", BigDecimal.valueOf(10.0),100,null,c1);
            productService.save(pizza);

        };
    }

}
