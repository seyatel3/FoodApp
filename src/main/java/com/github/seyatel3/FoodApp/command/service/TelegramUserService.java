package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * {@link Service} for handling {@link TelegramUser} entity.
 */
public interface TelegramUserService {

    /**
     * Save provided {@link TelegramUser} entity.
     *
     * @param  telegramUser provided telegram user.
     */
    void save(TelegramUser telegramUser);

    /**
     * Retrieve all active {@link TelegramUser}.
     *
     * @return the collection of the active {@link TelegramUser} objects.
     */
    List<TelegramUser> retrieveAllActiveUsers();

    /**
     * Retrieve all admin {@link TelegramUser}.
     *
     * @return the collection of the admin {@link TelegramUser} objects.
     */
    List<TelegramUser> retrieveAllAdminUsers();

    /**
     * Find {@link TelegramUser} by userName
     *
     * @param userName provided User Name
     * @return {@link TelegramUser} with provided user Name or null otherwise.
     */
    Optional<TelegramUser> findByUserName(String userName);

    /**
     * Find {@link TelegramUser} by chatId.
     *
     * @param chatId provided Chat ID
     * @return {@link TelegramUser} with provided chat ID or null otherwise.
     */
    Optional<TelegramUser> findByChatId(String chatId);

    /**
     * Find {@link TelegramUser} by chatId.
     *
     * @param chatId provided Chat ID
     * @return {@link TelegramUser} with provided chat ID or null otherwise.
     */
    TelegramUser findOneByChatId(String chatId);

    /**
     * Find name of {@link TelegramUser} by chatId.
     *
     * @param chatId provided Chat ID
     * @return String name with provided chat ID or null otherwise.
     */
    String getName(String chatId);
}
