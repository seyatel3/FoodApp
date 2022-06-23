package com.github.seyatel3.FoodApp.repository.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int idOrder;

    @JoinColumn(name = "chat_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String chatId;

    @Column(name = "date_time")
    private String date_time;

    @Column(name = "status")
    private boolean status;

    @Column(name = "summ")
    private BigDecimal summ;

}
