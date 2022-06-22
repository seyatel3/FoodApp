package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.repository.entity.Menu;
import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * {@link Service} for handling {@link Menu} entity.
 */
public interface MenuService {
    /**
     * Save provided {@link Menu} entity.
     *
     * @param  menu provided menu item.
     */
    void save(Menu menu);

    /**
     * Retrieve all active {@link Menu}.
     *
     * @return the collection of the {@link Menu} objects.
     */
    List<Menu> retrieveAll();

    /**
     * Find {@link Menu} by name
     *
     * @param name provided name
     * @return {@link Menu} with provided name or null otherwise.
     */
    Optional<Menu> findByName(String name);

    /**
     * Find {@link Menu} by id_menu.
     *
     * @param id_menu provided id_menu
     * @return {@link Menu} with provided ID or null otherwise.
     */
    Optional<Menu> findById(Integer id_menu);

    /**
     * Return all names  {@link Menu} .
     *
     * @return {@link Menu}
     */
    void menuUpdate(Integer id_menu, String name, BigDecimal price);

}
