package hk.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String number;
    @Column(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles = new ArrayList<>();

    public AppUser(Long id, String firstName, String lastName, String number, String username, String password, List<AppRole> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public AppUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AppRole> roles) {
        this.roles = roles;
    }
}
