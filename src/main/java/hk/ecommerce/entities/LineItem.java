package hk.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class LineItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @OneToMany(targetEntity = Product.class)
    @JsonManagedReference
    private List<Product> products;
    @ManyToOne
    @JsonBackReference(value = "lineItem_cart")
    private Cart cart;

    public LineItem(Long id, int quantity, List<Product> products, Cart cart) {
        this.id = id;
        this.quantity = quantity;
        this.products = products;
        this.cart = cart;
    }

    public LineItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
