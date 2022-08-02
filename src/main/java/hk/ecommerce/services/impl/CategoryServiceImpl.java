package hk.ecommerce.services.impl;

import hk.ecommerce.entities.Category;
import hk.ecommerce.jparepositories.CategoryRepository;
import hk.ecommerce.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean update(Long id, Category category) {
        if(categoryRepository.existsById(id)){
            category.setId(id);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteById(Long id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
