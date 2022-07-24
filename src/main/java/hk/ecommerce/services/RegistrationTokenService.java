package hk.ecommerce.services;

import hk.ecommerce.entities.AppUser;

public interface RegistrationTokenService {

    String generateToken(AppUser user);
    void deleteValidationToken(Long id);

}
