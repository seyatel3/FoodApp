package com.github.seyatel3.FoodApp.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.seyatel3.FoodApp.command.CommandName.NO;
import static com.github.seyatel3.FoodApp.command.NoCommand.NO_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit test for NoCommand")
 public class NoCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}