package hk.ecommerce.jparepositories;

import hk.ecommerce.entities.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder,Long> {

//    @Query("select o from UserOrder o where o.appUser.id = :id")
//    List<UserOrder> findAllByAppUser(@Param("id") Long id);
}
