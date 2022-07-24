package hk.ecommerce.controllers;

import hk.ecommerce.entities.Product;
import hk.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/category/id/{id}")
    public List<Product> getProductsByCategory(@PathVariable Long id) {
        return productService.findProductByCategory(id);
    }

    @PostMapping(value = "/")
    public String save(@RequestPart("imageFile") MultipartFile multipartFile, @RequestPart("product") Product product) throws IOException {
        product.setImage(multipartFile.getBytes());
        productService.save(product);
        return "upload successfull";
    }

    @PutMapping("/update/id/{id}")
    public void update(@PathVariable("id") Long id, Product product) {
        productService.updateById(id, product);
    }

    @DeleteMapping("/delete/id/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

}
