package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.command.service.TelegramUserService;
import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.github.seyatel3.FoodApp.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;


    public static final String ADMIN_HELP_MESSAGE = String.format("\uD83C\uDF55<b>Дотупные команды</b>\uD83C\uDF55\n\n"

                    + "%s - начать работу со мной\n"
                    + "%s - количество активных пользователей\n"
                    + "%s - показать что есть в меню\n"
                    + "%s - добавить новое блюдо в меню\n"
                    + "%s - изменить блюдо в меню\n"
                    + "%s - сделать заказ\n"
                    + "%s - отменить заказ\n"
                    + "%s - выйти за едой (закрывает открытые заказы)\n"
                    + "%s - приостановить работу со мной\n"
                    + "%s - получить помощь в работе со мной\n",
            START.getCommandName(), STAT.getCommandName(), MENU.getCommandName(),
            MENU_ADD_ITEM.getCommandName(), MENU_UPDATE.getCommandName(), ORDER.getCommandName(),
            CANCEL_ORDER.getCommandName(), LETS_GO.getCommandName(), STOP.getCommandName(),
            HELP.getCommandName());

    public static final String HELP_MESSAGE = String.format("\uD83C\uDF55<b>Дотупные команды</b>\uD83C\uDF55\n\n"

                    + "%s - начать работу со мной\n"
                    + "%s - количество активных пользователей\n"
                    + "%s - показать что есть в меню\n"
                    + "%s - сделать заказ\n"
                    + "%s - отменить заказ\n"
                    + "%s - приостановить работу со мной\n"
                    + "%s - получить помощь в работе со мной\n",
            START.getCommandName(), STAT.getCommandName(), MENU.getCommandName(),
            ORDER.getCommandName(), CANCEL_ORDER.getCommandName(), STOP.getCommandName(),
            HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        TelegramUser telegramUser = telegramUserService.findOneByChatId(chatId);
        if (telegramUser.isAdmin()) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), ADMIN_HELP_MESSAGE);
        } else {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
        }
    }
}
