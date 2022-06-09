package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.github.seyatel3.FoodApp.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {

    public static final String HELP_MESSAGE = String.format("\uD83C\uDF55<b>Дотупные команды</b>\uD83C\uDF55\n\n"

                    + "<b>Начать\\закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - количество активных пользователей"
                    + "%s - приостановить работу со мной\n\n"
                    + "%s - получить помощь в работе со мной\n",
            START.getCommandName(), STAT.getCommandName(), STOP.getCommandName(), HELP.getCommandName());
    private final SendBotMessageService sendBotMessageService;

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
