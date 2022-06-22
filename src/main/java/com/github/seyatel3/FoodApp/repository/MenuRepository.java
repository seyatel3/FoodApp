package com.github.seyatel3.FoodApp.repository;

import com.github.seyatel3.FoodApp.repository.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findAll();
    Optional<Menu> findByName(String name);

    @Modifying
    @Query("update Menu m set m.name = ?2, m.price = ?3 where m.id_menu = ?1")
    void menuUpdate(Integer id_menu, String name, BigDecimal price);

}
