package com.github.seyatel3.FoodApp.repository.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tg_user")
public class TelegramUser {
    @Id
    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "active")
    private boolean active;

    @Column(name = "admin")
    private boolean admin;
}
