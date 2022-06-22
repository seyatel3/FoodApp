package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.MenuService;
import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.command.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;
import static com.github.seyatel3.FoodApp.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, MenuService menuService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(MENU.getCommandName(), new MenuCommand(sendBotMessageService, menuService))
                .put(MENU_ADD_ITEM.getCommandName(), new MenuAddItemCommand(sendBotMessageService, menuService))
                .put(MENU_UPDATE.getCommandName(), new MenuUpdateCommand(sendBotMessageService, menuService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}