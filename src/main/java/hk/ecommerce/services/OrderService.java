package hk.ecommerce.services;

import hk.ecommerce.entities.UserOrder;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    void save(UserOrder order, Authentication authentication);
    List<UserOrder> findOrdersByAppUser(Long id);
    List<UserOrder> findAll();
}
