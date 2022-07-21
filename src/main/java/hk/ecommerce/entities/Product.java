package hk.ecommerce.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal unitPrice;
    private int stock;
    @Lob
    private byte[] image;

}
