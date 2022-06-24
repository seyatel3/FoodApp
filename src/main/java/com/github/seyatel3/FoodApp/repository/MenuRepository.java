package com.github.seyatel3.FoodApp.repository;

import com.github.seyatel3.FoodApp.repository.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findAll();

    Optional<Menu> findByName(String name);

    @Query(value = "SELECT price from Menu m WHERE m.idMenu = :id_menu")
    BigDecimal getPrice(@Param("id_menu") Integer id_menu);

    @Modifying
    @Query("update Menu m set m.name = ?2, m.price = ?3 where m.idMenu = ?1")
    void menuUpdate(Integer id_menu, String name, BigDecimal price);

    @Query("select name from Menu m where m.idMenu = :idMenu")
    String getName(@Param("idMenu") Integer idMenu);

}
