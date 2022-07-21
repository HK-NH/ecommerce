package hk.ecommerce.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class RegistrationToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne
    private AppUser user;
    private Instant expirationDate;

    public RegistrationToken(Long id, String token, AppUser user, Instant expirationDate) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expirationDate = expirationDate;
    }

    public RegistrationToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }
}
