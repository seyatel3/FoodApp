package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.repository.entity.Menu;
import com.github.seyatel3.FoodApp.repository.entity.Order;
import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * {@link Service} for handling {@link Order} entity.
 */
public interface OrderService {
    /**
     * Save provided {@link Menu} entity.
     *
     * @param order provided order item.
     */
    void save(Order order);

    /**
     * Find {@link Order} by chat_id
     *
     * @param chat_id provided chat_id
     * @return {@link Order} with provided chat_id or null otherwise.
     */
    Optional<Order> findByChatId(String chat_id);

    /**
     * Find {@link Order} by id_order.
     *
     * @param id_order provided id_order
     * @return {@link Order} with provided ID or null otherwise.
     */
    Order findByIdOrder(Integer id_order);

    /**
     * Delete {@link Order} by id_order.
     * @param id_order provided id_order
     */
    void deleteByIdOrder(Integer id_order);

    /**
     * Retrieve all open {@link Order}.
     *
     * @return the collection of the active {@link Order} objects.
     */
    List<Order>  getAllByStatusIsTrue();

}
