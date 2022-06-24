package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.repository.MenuRepository;
import com.github.seyatel3.FoodApp.repository.OrderRepository;
import com.github.seyatel3.FoodApp.repository.entity.Menu;
import com.github.seyatel3.FoodApp.repository.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(Order order) {orderRepository.save(order);}
    @Override
    public Optional<Order> findByChatId(String chat_id) {return orderRepository.findByChatId(chat_id);}
    @Override
    public Order findByIdOrder(Integer id_order) {return orderRepository.findByIdOrder(id_order);}
    @Override
    public void deleteByIdOrder(Integer id_order) {orderRepository.deleteByIdOrder(id_order);}

    @Override
    public List<Order> getAllByStatusIsTrue() {return orderRepository.getAllByStatusIsTrue();}
}
