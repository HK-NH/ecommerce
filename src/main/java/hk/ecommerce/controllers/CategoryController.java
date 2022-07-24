package hk.ecommerce.controllers;

import hk.ecommerce.entities.Category;
import hk.ecommerce.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody Category category){
        categoryService.save(category);
    }

    @GetMapping("/update/id/{id}")
    public Boolean update(Long id,Category category){
        return categoryService.update(id,category);
    }

    @DeleteMapping("/delete/id/{id}")
    public Boolean delete(Long id){
        return categoryService.deleteById(id);
    }
}
