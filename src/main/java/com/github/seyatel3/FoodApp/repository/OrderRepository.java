package com.github.seyatel3.FoodApp.repository;

import com.github.seyatel3.FoodApp.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByChatId(String chat_id);
    Order findByIdOrder(Integer id_order);
    @Transactional
    void deleteByIdOrder(Integer id_order);

    List<Order> getAllByStatusIsTrue();
}
