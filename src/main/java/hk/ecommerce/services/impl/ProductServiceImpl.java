package hk.ecommerce.services.impl;

import hk.ecommerce.elasticrepositories.ProductElasticRepository;
import hk.ecommerce.entities.Product;
import hk.ecommerce.jparepositories.ProductRepository;
import hk.ecommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductElasticRepository productElasticRepository;
    private Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    public ProductServiceImpl(ProductRepository productRepository,ProductElasticRepository productElasticRepository) {
        this.productRepository = productRepository;
        this.productElasticRepository = productElasticRepository;
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
        productElasticRepository.save(product);
    }

    @Override
    public Iterable<Product> findAll() {
//        return productRepository.findAll();
        return productElasticRepository.findAll();
    }

    @Override
    public Page<Product> findProductByCategory(Long id, Pageable pageable) {
//        return productRepository.findAllByCategory_Id(id);
        return productElasticRepository.findAllByCategory_Id(id, PageRequest.of(0, 10));
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            productElasticRepository.deleteById(id);
            return "true";
        }
        else
            return "false";
    }

    @Override
    @Transactional
    public String  updateById(Long id, Product product) {
        if(productRepository.existsById(id)){
            product.setId(id);
            productRepository.save(product);
            productElasticRepository.save(product);
            return "true";
        }
        else
            return "false";
    }

}
