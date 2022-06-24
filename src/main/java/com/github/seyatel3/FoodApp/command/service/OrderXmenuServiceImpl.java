package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.repository.OrderRepository;
import com.github.seyatel3.FoodApp.repository.OrderXmenuRepository;
import com.github.seyatel3.FoodApp.repository.entity.Order;
import com.github.seyatel3.FoodApp.repository.entity.OrderXmenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderXmenuServiceImpl implements OrderXmenuService{

    private final OrderXmenuRepository orderXmenuRepository;

    @Autowired
    public OrderXmenuServiceImpl(OrderXmenuRepository orderXmenuRepository) {
        this.orderXmenuRepository = orderXmenuRepository;
    }

    @Override
    public void save(OrderXmenu orderXmenu) {orderXmenuRepository.save(orderXmenu);}
    @Override
    public Optional<OrderXmenu> findByIdOrder(Integer id_order) {return orderXmenuRepository.findByIdOrder(id_order);}
    @Override
    public void deleteByIdOrder(Integer id_order) {orderXmenuRepository.deleteByIdOrder(id_order);}
    @Override
    public List<Integer> getMenuIdList(Integer idOrder){return  orderXmenuRepository.getMenuIdList(idOrder);}
}
