package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.*;
import com.github.seyatel3.FoodApp.repository.entity.Order;
import com.github.seyatel3.FoodApp.repository.entity.OrderXmenu;
import com.github.seyatel3.FoodApp.repository.entity.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.seyatel3.FoodApp.command.CommandName.ORDER;

public class LetsGoCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final MenuService menuService;
    private final OrderService orderService;
    private final OrderXmenuService orderXmenuService;
    private final TelegramUserService telegramUserService;

    public LetsGoCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, MenuService menuService, OrderService orderService, OrderXmenuService orderXmenuService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
        this.menuService = menuService;
        this.orderService = orderService;
        this.orderXmenuService = orderXmenuService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        TelegramUser telegramUser = telegramUserService.findOneByChatId(chatId);
        if (telegramUser.isAdmin()) {
            BigDecimal sum;
            List<Order> orders = orderService.getAllByStatusIsTrue();
            sum = orders.stream()
                    .map(Order::getSumm)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            List<String> menuNames = new ArrayList<>();

            for (Order value : orders) {
                String orderChatId = value.getChatId();
                int orderId = value.getIdOrder();
                String userName = telegramUserService.getName(orderChatId);
                BigDecimal price = value.getSumm();
                List<Integer> orderMenuIds = orderXmenuService.getMenuIdList(orderId);
                for (Integer orderMenuId : orderMenuIds) {
                    menuNames.add(menuService.getName(orderMenuId));
                }
                sendBotMessageService.sendMessage(chatId, userName + " " + Arrays.toString(menuNames.toArray()) + " " + price + " рублей");
            }

            orders.forEach(order -> order.setStatus(false));
            orders.forEach(orderService::save);
            sendBotMessageService.sendMessage(chatId, "Общая сумма:  " + sum + " рублей");
        } else {
            sendBotMessageService.sendMessage(chatId, "Эта команда только для админов");
        }


    }
}