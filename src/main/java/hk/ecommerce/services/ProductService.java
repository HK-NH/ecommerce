package hk.ecommerce.services;

import hk.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    void save(Product product);
    Iterable<Product> findAll();
    Page<Product> findProductByCategory(Long id, Pageable pageable);
    String deleteById(Long id);
    String updateById(Long id,Product product);
}
