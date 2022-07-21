package hk.ecommerce.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        if(customUserDetails.isEnabled())
        return checkPassword(customUserDetails, password, passwordEncoder);
        else
            throw new DisabledException("Account Disabled");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomUserDetails customUserDetails, String rawPassword, PasswordEncoder passwordEncoder) {
        if (passwordEncoder.matches(rawPassword, customUserDetails.getPassword()) ) {
            return new UsernamePasswordAuthenticationToken(customUserDetails.getUsername(), customUserDetails.getPassword(), customUserDetails.getAuthorities());
        } else
            throw new BadCredentialsException("Authentication Unsuccessfull");
    }
}
