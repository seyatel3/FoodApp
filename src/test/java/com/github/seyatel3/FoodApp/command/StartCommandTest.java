package com.github.seyatel3.FoodApp.command;

import static com.github.seyatel3.FoodApp.command.CommandName.START;
import static com.github.seyatel3.FoodApp.command.StartCommand.START_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

public class StartCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return START.getCommandName();
    }
    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService);
    }
}