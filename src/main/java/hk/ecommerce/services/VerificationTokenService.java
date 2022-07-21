package hk.ecommerce.services;

import hk.ecommerce.entities.AppUser;

public interface VerificationTokenService {

    void generateToken(AppUser user);
}
