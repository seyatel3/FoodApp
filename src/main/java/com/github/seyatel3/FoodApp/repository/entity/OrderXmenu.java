package com.github.seyatel3.FoodApp.repository.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "orderxmenu")

public class OrderXmenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JoinColumn(name = "id_order", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int idOrder;

    @JoinColumn(name = "id_menu", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int idMenu;
}
