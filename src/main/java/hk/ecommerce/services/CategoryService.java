package hk.ecommerce.services;

import hk.ecommerce.entities.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryService {

    void save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAll();
    Boolean update(Long id,Category category);
    Boolean deleteById(Long id);
}
