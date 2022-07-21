package hk.ecommerce.services.impl;

import hk.ecommerce.entities.AppRole;
import hk.ecommerce.entities.AppUser;
import hk.ecommerce.repositories.AppRoleRepository;
import hk.ecommerce.repositories.AppUserRepository;
import hk.ecommerce.repositories.VerificationTokenRepository;
import hk.ecommerce.security.CustomUserDetails;
import hk.ecommerce.services.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder, VerificationTokenRepository verificationTokenRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public void saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("User not found!");
        AppUser appUser = appUserRepository.findAppUserByUsername(username).orElseThrow(s);
        Supplier<UsernameNotFoundException> r =
                () -> new UsernameNotFoundException("Role not found!");
        AppRole appRole = appRoleRepository.findAppRoleByRoleName(roleName).orElseThrow(r);
        appUser.getRoles().add(appRole);
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser findAppUserByUsername(String username) {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("User not found!");
        return appUserRepository.findAppUserByUsername(username).orElseThrow(s);
    }

    @Override
    public List<AppUser> getListUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public Boolean validateUser(String token) {
        AppUser user = verificationTokenRepository.findVerificationTokenByToken(token).getUser();
        if(user != null){
            user.setActive(true);
            appUserRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");
        AppUser u =appUserRepository.findAppUserByUsername(username).orElseThrow(s);
        return new CustomUserDetails(u);
    }
}
