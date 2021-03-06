package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.*;
import com.github.seyatel3.FoodApp.repository.OrderXmenuRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for ComandContainer")
class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init(){
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        MenuService menuService = Mockito.mock(MenuService.class);
        OrderService orderService = Mockito.mock(OrderService.class);
        OrderXmenuService orderXmenuService = Mockito.mock(OrderXmenuService.class);
        commandContainer = new CommandContainer(sendBotMessageService, telegramUserService, menuService, orderService, orderXmenuService);
    }


    @Test
    void ShouldGetAllExistingComands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }
    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/fgjhdfgdfg";

        //when
        Command command = commandContainer.retrieveCommand(unknownCommand);

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }

}