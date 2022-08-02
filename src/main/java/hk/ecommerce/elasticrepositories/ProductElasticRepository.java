package hk.ecommerce.elasticrepositories;

import hk.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductElasticRepository extends ElasticsearchRepository<Product,Long> {

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"category.id\": \"?0\"}}]}}")
    Page<Product> findAllByCategory_Id(Long id, Pageable pageable);
}
