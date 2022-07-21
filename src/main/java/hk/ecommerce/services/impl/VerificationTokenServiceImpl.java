package hk.ecommerce.services.impl;

import hk.ecommerce.entities.AppUser;
import hk.ecommerce.entities.RegistrationToken;
import hk.ecommerce.repositories.VerificationTokenRepository;
import hk.ecommerce.services.VerificationTokenService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    public final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public String generateToken(AppUser user) {
        String randomUUID = UUID.randomUUID().toString();
        RegistrationToken token = new RegistrationToken();
        token.setToken(randomUUID);
        token.setUser(user);
        verificationTokenRepository.save(token);
        return randomUUID;
    }
}
