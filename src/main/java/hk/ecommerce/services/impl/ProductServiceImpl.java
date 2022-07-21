package hk.ecommerce.services.impl;

import hk.ecommerce.entities.Product;
import hk.ecommerce.repositories.ProductRepository;
import hk.ecommerce.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductByCategory(Long id) {
        return productRepository.findAllByCategory_Id(id);
    }

    @Override
    public Boolean deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public Boolean updateById(Long id, Product product) {
        if(productRepository.existsById(id)){
            product.setId(id);
            productRepository.save(product);
            return true;
        }
        else
            return false;
    }

}
