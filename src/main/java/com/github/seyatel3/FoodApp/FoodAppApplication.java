package com.github.seyatel3.FoodApp;

import com.github.seyatel3.FoodApp.Bot.KristaFoodBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication
public class FoodAppApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(FoodAppApplication.class, args);
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		botsApi.registerBot(new KristaFoodBot());
	}


}
