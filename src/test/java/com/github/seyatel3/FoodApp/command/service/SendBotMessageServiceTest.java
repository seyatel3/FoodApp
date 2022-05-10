package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.Bot.KristaFoodBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit test for SendBotMassageService")
public class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private KristaFoodBot kristaFoodBot;

    @BeforeEach
    public void init(){
        kristaFoodBot = Mockito.mock(KristaFoodBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(kristaFoodBot);
    }

    @Test
    public void shouldProperlyReadMessage() throws TelegramApiException {

        //given
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        //when
        sendBotMessageService.sendMessage(chatId,message);

        //then
        Mockito.verify(kristaFoodBot).execute(sendMessage);
    }

}