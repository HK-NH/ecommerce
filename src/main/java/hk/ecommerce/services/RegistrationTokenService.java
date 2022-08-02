package hk.ecommerce.services;

import hk.ecommerce.entities.AppUser;
import hk.ecommerce.entities.RegistrationToken;

public interface RegistrationTokenService {

    String generateToken(AppUser user);
    void deleteValidationToken(Long id);
    public RegistrationToken findVerificationTokenByToken(String token);
}
