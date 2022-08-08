package hk.ecommerce.controllers;

import hk.ecommerce.entities.UserOrder;
import hk.ecommerce.services.AppUserService;
import hk.ecommerce.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final AppUserService appUserService;

    private Logger logger = Logger.getLogger(OrderController.class.getName());

    public OrderController(OrderService orderService, AppUserService appUserService) {
        this.orderService = orderService;
        this.appUserService = appUserService;
    }

    @PostMapping("/")
    public void save(@RequestBody UserOrder order, Authentication authentication) {
        order.setAppUser(appUserService.findAppUserByUsername(authentication.getName()));
        orderService.save(order, authentication);
    }

    @GetMapping("/user/id/{id}")
    public List<UserOrder> findOrdersByAppUser(@PathVariable Long id) {
        return orderService.findOrdersByAppUser(id);
    }

    @GetMapping("/")
    public List<UserOrder> findAll() {
        return orderService.findAll();
    }
}
