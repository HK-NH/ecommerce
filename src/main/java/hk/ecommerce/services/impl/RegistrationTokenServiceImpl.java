package hk.ecommerce.services.impl;

import hk.ecommerce.entities.AppUser;
import hk.ecommerce.entities.RegistrationToken;
import hk.ecommerce.repositories.RegistrationTokenRepository;
import hk.ecommerce.services.RegistrationTokenService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationTokenServiceImpl implements RegistrationTokenService {

    public final RegistrationTokenRepository verificationTokenRepository;

    public RegistrationTokenServiceImpl(RegistrationTokenRepository verificationTokenRepository) {
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

    @Override
    public void deleteValidationToken(Long id) {
        if(verificationTokenRepository.existsById(id)){
            verificationTokenRepository.deleteById(id);
        }
    }
}
