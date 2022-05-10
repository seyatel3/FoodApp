package com.github.seyatel3.FoodApp.command;

import static com.github.seyatel3.FoodApp.command.CommandName.STOP;
import static com.github.seyatel3.FoodApp.command.StopCommand.STOP_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

public class StopCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService);
    }
}