package com.github.seyatel3.FoodApp.repository;

import com.github.seyatel3.FoodApp.repository.entity.OrderXmenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderXmenuRepository extends JpaRepository<OrderXmenu, Integer> {

    Optional<OrderXmenu> findByIdOrder(Integer id_order);

    @Query(value = "select idMenu from OrderXmenu o where o.idOrder = :idOrder")
    List<Integer> getMenuIdList(@Param("idOrder") Integer idOrder);

    @Transactional
    void deleteByIdOrder(Integer id_order);

}
