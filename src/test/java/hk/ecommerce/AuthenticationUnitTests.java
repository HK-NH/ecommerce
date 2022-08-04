package hk.ecommerce;

import hk.ecommerce.entities.AppUser;
import hk.ecommerce.entities.RegistrationToken;
import hk.ecommerce.services.RegistrationTokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthenticationUnitTests {

    @Test
    @DisplayName("Test for successfull validation of user")
    public void userValidationHappyFlow(){
        RegistrationTokenService registrationTokenService = mock(RegistrationTokenService.class);
        RegistrationToken token = mock(RegistrationToken.class);
        String tokenString = "justForTest";
        token.setToken(tokenString);
        given(registrationTokenService.findVerificationTokenByToken(tokenString)).willReturn(token);
        AppUser appUser = mock(AppUser.class);
        token.setUser(appUser);
        registrationTokenService.deleteValidationToken(token.getId());
        given(registrationTokenService.findVerificationTokenByToken(tokenString)).willReturn(null);

    }
}
