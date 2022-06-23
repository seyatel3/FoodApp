package com.github.seyatel3.FoodApp.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private int idMenu;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

}
