package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.Bot.KristaFoodBot;
import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.command.service.SendBotMessageServiceImpl;
import com.github.seyatel3.FoodApp.command.service.TelegramUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class AbstractCommandTest {

    protected KristaFoodBot kristaFoodBot = Mockito.mock(KristaFoodBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(kristaFoodBot);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);

    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();

    @Test
    public void ShouldProperlyExecuteCommands() throws TelegramApiException {

        Long chatId = 1234567891234L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        //when
        getCommand().execute(update);

        //then
        Mockito.verify(kristaFoodBot).execute(sendMessage);
    }

}
