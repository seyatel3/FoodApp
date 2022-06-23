package com.github.seyatel3.FoodApp.command;

import com.github.seyatel3.FoodApp.command.service.OrderService;
import com.github.seyatel3.FoodApp.command.service.OrderXmenuService;
import com.github.seyatel3.FoodApp.command.service.SendBotMessageService;
import com.github.seyatel3.FoodApp.repository.entity.Order;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.seyatel3.FoodApp.command.CommandName.ORDER;

public class CancelOrderCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final OrderService orderService;
    private  final OrderXmenuService orderXmenuService;

    public final static String CANCEL_ORDER_NO_ARG_MESSAGE = "Чтобы отменить заказ введите номер заказа";

    public CancelOrderCommand(SendBotMessageService sendBotMessageService, OrderService orderService, OrderXmenuService orderXmenuService) {
        this.sendBotMessageService = sendBotMessageService;
        this.orderService = orderService;
        this.orderXmenuService = orderXmenuService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        if (update.getMessage().getText().equalsIgnoreCase(ORDER.getCommandName())) {
            sendBotMessageService.sendMessage(chatId, CANCEL_ORDER_NO_ARG_MESSAGE);
        } else {
            String[] message = update.getMessage().getText().split(" ");
            Order order = orderService.findByIdOrder(Integer.parseInt(message[1]));
            if (!order.isStatus()){
                sendBotMessageService.sendMessage(chatId, "Нельзя отменить закрытый заказ");
            } else if(order == null){
                sendBotMessageService.sendMessage(chatId, "Заказа с таким номером не существует");
            } else if (order.getChatId().equals(chatId)) {
                orderXmenuService.deleteByIdOrder(Integer.parseInt(message[1]));
                orderService.deleteByIdOrder(Integer.parseInt(message[1]));
                sendBotMessageService.sendMessage(chatId, "Вы успешно отменили заказ номер: " + message[1]);
            } else {
                sendBotMessageService.sendMessage(chatId, "У Вас нет заказа с таким номером");
            }
        }
    }
}




