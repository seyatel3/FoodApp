package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.MenuService;
import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.command.service.TelegramUserService;
import com.github.seyatel3.FoodApp.repository.entity.Menu;
import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.seyatel3.FoodApp.command.CommandName.MENU_UPDATE;

/**
 * MenuUpdate {@link Command}.
 */
public class MenuUpdateCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final MenuService menuService;
    private final TelegramUserService telegramUserService;

    @Autowired
    public MenuUpdateCommand(SendBotMessageService sendBotMessageService, MenuService menuService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.menuService = menuService;
        this.telegramUserService = telegramUserService;
    }
    public static String MENU_UPDATE_NO_ARG_MESSAGE =
            "Чтобы изменить блюдо из списка меню нужно указать его id \n " +
            "(id можно узнать с помощью команды /menu) написать его новое имя \n" +
            "если имя состоит из нескольки слов используй _ ) и новую цену через пробел \n" +
            "Например /menu_update 1 Кура_гриль 200";

    public static String MENU_NO_UPDATE_MESSAGE = "Блюда с указанным id не существует.";

    public static String MENU_UPDATE_MESSAGE = "Блюдо с id %s успешно изменено на \n" +
            "Название: %s  Цена: %s";

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        TelegramUser telegramUser = telegramUserService.findOneByChatId(chatId);
        if (telegramUser.isAdmin()) {
            if (update.getMessage().getText().equalsIgnoreCase(MENU_UPDATE.getCommandName())) {
            sendBotMessageService.sendMessage(chatId, MENU_UPDATE_NO_ARG_MESSAGE);
            } else {
                String[] message = update.getMessage().getText().toString().split(" ");
                Integer menu_id = Integer.parseInt(message[1]);
                String name = message[2];
                BigDecimal price = new BigDecimal(message[3]);

                menuService.findById(menu_id).ifPresentOrElse(
                        menu -> {
                            menu.setName(name);
                            menu.setPrice(price);
                            menuService.save(menu);
                            sendBotMessageService.sendMessage(chatId, String.format(MENU_UPDATE_MESSAGE, menu_id, name, price));
                        },
                        () -> {
                            sendBotMessageService.sendMessage(chatId, MENU_NO_UPDATE_MESSAGE);
                        });
            }
        } else {
            sendBotMessageService.sendMessage(chatId, "Эта команда только для админов");
        }
    }
}