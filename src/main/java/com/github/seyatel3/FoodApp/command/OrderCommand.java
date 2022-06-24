package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.MenuService;
import com.github.seyatel3.FoodApp.command.service.OrderService;
import com.github.seyatel3.FoodApp.command.service.OrderXmenuService;
import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.repository.entity.Order;
import com.github.seyatel3.FoodApp.repository.entity.OrderXmenu;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.github.seyatel3.FoodApp.command.CommandName.ORDER;

public class OrderCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final MenuService menuService;
    private final OrderService orderService;
    private final OrderXmenuService orderXmenuService;

    public final static String ORDER_NO_ARG_MESSAGE = "Чтобы заказать одно или несколько блюд, введите через пробел " +
            "id блюд (id можно узнать используя команду /menu ) \n" +
            "Например чтоб заказать 2 пиццы введите /order 1 1";
    public final static String ORDER_ERROR_MESSAGE = "Что то пошло не так. Убедитесь что Вы использовали только существующие id блюд";


    public OrderCommand(SendBotMessageService sendBotMessageService, MenuService menuService, OrderService orderService, OrderXmenuService orderXmenuService) {
        this.sendBotMessageService = sendBotMessageService;
        this.menuService = menuService;
        this.orderService = orderService;
        this.orderXmenuService = orderXmenuService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String date_time = dtf.format(now);

        if (update.getMessage().getText().equalsIgnoreCase(ORDER.getCommandName())) {
            sendBotMessageService.sendMessage(chatId, ORDER_NO_ARG_MESSAGE);
        } else {
            String[] message = update.getMessage().getText().split(" ");

            menuService.findById(Integer.parseInt(message[1])).ifPresentOrElse(
                    orders -> {
                        Order order = new Order();
                        order.setChatId(chatId);
                        order.setDate_time(date_time);
                        order.setStatus(true);
                        order.setSumm(new BigDecimal("0"));
                        orderService.save(order);
                        BigDecimal summ = new BigDecimal("0");

                        for (int i = 1; i < message.length; i++) {
                            OrderXmenu orderXmenu;
                            summ = summ.add(menuService.getPrice(Integer.parseInt(message[i])));
                            if((menuService.findById(Integer.parseInt(message[i]))).isPresent()){
                                    orderXmenu = new OrderXmenu();
                                    orderXmenu.setIdOrder(order.getIdOrder());
                                    orderXmenu.setIdMenu(Integer.parseInt(message[i]));
                                    orderXmenuService.save(orderXmenu);
                            }else {
                                sendBotMessageService.sendMessage(chatId, "Блюда с id " + message[i] + " не существует");
                            }

                        }
                        order.setSumm(summ);
                        orderService.save(order);
                        sendBotMessageService.sendMessage(chatId, "Принято! Номер Вашего заказа: " + order.getIdOrder() + ". Сумма заказа: " + order.getSumm());
                    },
                    () ->{
                        sendBotMessageService.sendMessage(chatId, ORDER_ERROR_MESSAGE);
                    }
            );

        }
    }
}

