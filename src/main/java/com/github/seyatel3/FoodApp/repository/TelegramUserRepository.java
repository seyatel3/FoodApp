package com.github.seyatel3.FoodApp.repository;

import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, String> {
    List<TelegramUser> findAllByActiveTrue();
    List<TelegramUser> findAllByAdminTrue();
    TelegramUser findOneByChatId(String chatId);

    @Query(value = "select userName from TelegramUser t where t.chatId = :chatId")
    String getName(@Param("chatId") String chatId);
}
