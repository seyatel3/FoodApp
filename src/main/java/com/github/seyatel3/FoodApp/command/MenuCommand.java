package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.MenuService;
import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.repository.MenuRepository;
import com.github.seyatel3.FoodApp.repository.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Menu {@link Command}.
 */
public class MenuCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final MenuService menuService;

    @Autowired
    public MenuCommand(SendBotMessageService sendBotMessageService, MenuService menuService) {
        this.sendBotMessageService = sendBotMessageService;
        this.menuService = menuService;
    }
    public static String MENU_MESSAGE = "Сегодня в меню: \n" + "%s";

    @Override
    public void execute(Update update) {

        List<Menu> menu = menuService.retrieveAll();
        String menuItems = menu.stream()
                .map(menuIt -> String.format("%s %s %s \n", menuIt.getIdMenu(), menuIt.getName(), menuIt.getPrice()))
                .collect(Collectors.joining());

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format(MENU_MESSAGE, menuItems));
    }
}
