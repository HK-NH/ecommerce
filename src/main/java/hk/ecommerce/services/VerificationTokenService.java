package hk.ecommerce.services;

import hk.ecommerce.entities.AppUser;

public interface VerificationTokenService {

    String generateToken(AppUser user);
}
