package hk.ecommerce.repositories;

import hk.ecommerce.entities.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder,Long> {

//    @Query("select o from UserOrder o where o.appUser.id = :id")
//    List<UserOrder> findAllByAppUser(@Param("id") Long id);
}
