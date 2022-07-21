package hk.ecommerce.services;

import hk.ecommerce.entities.AppUser;

import java.util.List;

public interface AppUserService {
    void saveUser(AppUser appUser);
    void addRoleToUser(String username, String roleName);
    AppUser findAppUserByUsername(String username);
    List<AppUser> getListUsers();
    Boolean validateUser(String token);
}
