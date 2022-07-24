package hk.ecommerce.entities;


import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant creationTime;
    @OneToMany(targetEntity = LineItem.class,cascade = CascadeType.ALL)
    private List<LineItem> lineItems;
    @ManyToOne(targetEntity = AppUser.class)
    private AppUser appUser;

    public UserOrder(Long id, Instant creationTime, List<LineItem> lineItems, AppUser appUser) {
        this.id = id;
        this.creationTime = Instant.now();
        this.lineItems = lineItems;
        this.appUser = appUser;
    }

    public UserOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
