package hk.ecommerce.services;

import hk.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    void save(Product product);
    List<Product> findAll();
    List<Product> findProductByCategory(Long id);
    Boolean deleteById(Long id);
    Boolean updateById(Long id,Product product);
}
