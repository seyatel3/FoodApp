package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.command.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stat {@link Command}.
 */
public class StatCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String STAT_MESSAGE = "Bot использует %s человек.";

    @Autowired
    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        int activeUserCount = telegramUserService.retrieveAllActiveUsers().size();

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format(STAT_MESSAGE, activeUserCount));
    }
}
