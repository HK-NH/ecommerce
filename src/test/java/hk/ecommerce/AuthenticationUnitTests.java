package hk.ecommerce;

import hk.ecommerce.entities.AppUser;
import hk.ecommerce.entities.RegistrationToken;
import hk.ecommerce.services.RegistrationTokenService;
import hk.ecommerce.vo.LoginForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static hk.ecommerce.TestUtils.asJsonString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AuthenticationUnitTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Authentication with wrong credentials")
    public void loginwithWrongUser() throws Exception {
//        mvc.perform(post("/api/auth/login",new LoginForm("NonExistingUser","123")))
//                .andExpect(status().isUnauthorized());
        mvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new LoginForm("NonExistingUser", "123")))
//                        .content(asJsonString(new MockHttpServletRequest())))
        ).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test for successfull validation of user")
    public void userValidationHappyFlow() {
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

    @Test
    @DisplayName("Test for unauthenticated user")
    public void unauthenticated() throws Exception {
        mvc.perform(post("/api/orders/")).andExpect(status().isForbidden());
    }
}
