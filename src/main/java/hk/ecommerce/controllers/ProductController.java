package hk.ecommerce.controllers;

import hk.ecommerce.entities.Category;
import hk.ecommerce.entities.Product;
import hk.ecommerce.services.CategoryService;
import hk.ecommerce.services.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final Logger logger = Logger.getLogger(ProductController.class.getName());

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public Iterable<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/category/id/{id}")
    public Iterable<Product> getProductsByCategory(@PathVariable Long id) {
        return productService.findProductByCategory(id, PageRequest.of(0, 2));
    }

    @PostMapping(value = "/")
    public String save(@RequestPart("imageFile") MultipartFile multipartFile, @RequestPart("product") Product product) throws IOException {
        product.setImage(multipartFile.getBytes());
        Long categoryId = product.getCategory().getId();
        Category category = categoryService.findById(categoryId).get();
        product.getCategory().setName(category.getName());
        productService.save(product);
        return "upload successfull";
    }

    @PutMapping("/update/id/{id}")
    public void update(@PathVariable("id") Long id, Product product) {
        productService.updateById(id, product);
    }

    @DeleteMapping("/delete/id/{id}")
    public String delete(@PathVariable("id") Long id) {
        return productService.deleteById(id);
    }

}
