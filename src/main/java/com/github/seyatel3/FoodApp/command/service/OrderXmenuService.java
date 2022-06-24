package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.repository.entity.Menu;
import com.github.seyatel3.FoodApp.repository.entity.Order;
import com.github.seyatel3.FoodApp.repository.entity.OrderXmenu;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * {@link Service} for handling {@link OrderXmenu} entity.
 */
public interface OrderXmenuService {
    /**
     * Save provided {@link OrderXmenu entity.
     * @param orderXmenu provided orderXmenu item.
     */
    void save(OrderXmenu orderXmenu);

    /**
     * Find {@link OrderXmenu} by id_order
     *
     * @param id_order provided chat_id
     * @return {@link OrderXmenu} with provided id_order or null otherwise.
     */
    Optional<OrderXmenu> findByIdOrder(Integer id_order);


    /**
     * Delete {@link OrderXmenu} by id_order.
     * @param id_order provided id_order
     */
    void deleteByIdOrder(Integer id_order);

    /**
     * retrive all menu_ids {@link OrderXmenu} by id_order
     *
     * @param idOrder provided idOrders
     * @return List of menu_ids or null otherwise.
     */
    List<Integer> getMenuIdList(Integer idOrder);

}
