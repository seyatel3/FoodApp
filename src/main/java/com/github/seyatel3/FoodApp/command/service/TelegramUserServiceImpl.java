package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.repository.TelegramUserRepository;
import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return telegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public List<TelegramUser> retrieveAllAdminUsers() {
        return telegramUserRepository.findAllByAdminTrue();
    }

    @Override
    public Optional<TelegramUser> findByUserName(String userName) {
        return telegramUserRepository.findById(userName);
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return telegramUserRepository.findById(chatId);
    }
    @Override
    public TelegramUser findOneByChatId(String chatId) {return telegramUserRepository.findOneByChatId(chatId);}
    public String getName(String chatId) {return  telegramUserRepository.getName(chatId);}
}
