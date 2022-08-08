package hk.ecommerce.services;

import hk.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    void save(Product product);
    Page<Product> findAll(int page,int size);
    Page<Product> findProductByCategory(Long id);
    String deleteById(Long id);
    String updateById(Long id,Product product);
}
