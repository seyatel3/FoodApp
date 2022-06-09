package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.command.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    //todo добавить внятное начальное сообщение
    public final static String START_MESSAGE = "In time here might appear some useful message";


    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }


    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String userName = update.getMessage().getFrom().getUserName();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUser.setUserName(userName);
                    telegramUserService.save(telegramUser);
                });

        sendBotMessageService.sendMessage(chatId, START_MESSAGE);


    }
}

