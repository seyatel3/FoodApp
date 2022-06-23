package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.MenuService;
import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.command.service.TelegramUserService;
import com.github.seyatel3.FoodApp.repository.entity.Menu;
import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;

import static com.github.seyatel3.FoodApp.command.CommandName.MENU_ADD_ITEM;

public class MenuAddItemCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final MenuService menuService;

    private final TelegramUserService telegramUserService;

    public final static String ADD_ITEM_NO_ARG_MESSAGE = "Чтобы добавить новое блюдо в меню нужно написать его имя " +
            "(если имя состоит из нескольки слов используй _ ) и цену через пробел \n" +
            "Например /menu_add_item Кура_гриль 150";
    public final static String ADD_ITEM_MESSAGE = "Вы добавили в меню новое блюдо: \n Название: %s  Цена: %s";


    public MenuAddItemCommand(SendBotMessageService sendBotMessageService, MenuService menuService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.menuService = menuService;
        this.telegramUserService = telegramUserService;
    }


    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        TelegramUser telegramUser = telegramUserService.findOneByChatId(chatId);
        if (telegramUser.isAdmin()) {

            if (update.getMessage().getText().equalsIgnoreCase(MENU_ADD_ITEM.getCommandName())) {
                sendBotMessageService.sendMessage(chatId, ADD_ITEM_NO_ARG_MESSAGE);
            }else {
                String[] message = update.getMessage().getText().toString().split(" ");
                String name = message[1];
                BigDecimal price = new BigDecimal(message[2]);

                menuService.findByName(name).ifPresentOrElse(
                        menu -> {
                            sendBotMessageService.sendMessage(chatId, "Блюдо с именем " + name + " уже существует.");
                        },
                        () -> {
                            Menu menu = new Menu();
                            menu.setName(name);
                            menu.setPrice(price);
                            menuService.save(menu);
                            sendBotMessageService.sendMessage(chatId, String.format(ADD_ITEM_MESSAGE, name, price));
                        });
            }


        } else {
            sendBotMessageService.sendMessage(chatId, "Эта команда только для админов");
        }
    }
}

