package hk.ecommerce.services.impl;

import hk.ecommerce.entities.UserOrder;
import hk.ecommerce.jparepositories.AppUserRepository;
import hk.ecommerce.jparepositories.OrderRepository;
import hk.ecommerce.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AppUserRepository appUserRepository;

    public OrderServiceImpl(OrderRepository orderRepository, AppUserRepository appUserRepository) {
        this.orderRepository = orderRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void save(UserOrder order, Authentication authentication) {
        Supplier<UsernameNotFoundException> usernameNotFoundExceptionSupplier =
                () -> new UsernameNotFoundException("User not found!");
//        order.setAppUser(appUserRepository.findAppUserByUsername(authentication.getName()).orElseThrow(usernameNotFoundExceptionSupplier));
        orderRepository.save(order);
    }

    @Override
    public List<UserOrder> findOrdersByAppUser(Long id) {
//        return orderRepository.findAllByAppUser(id);
        return null;
    }

    @Override
    public List<UserOrder> findAll() {
        return orderRepository.findAll();
    }
}
