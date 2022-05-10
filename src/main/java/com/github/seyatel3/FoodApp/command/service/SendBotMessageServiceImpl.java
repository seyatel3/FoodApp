package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.Bot.KristaFoodBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */
@Service

public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final KristaFoodBot kristaFoodBot;

    @Autowired
    public SendBotMessageServiceImpl(KristaFoodBot kristaFoodBot) {
        this.kristaFoodBot = kristaFoodBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            kristaFoodBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }
}
