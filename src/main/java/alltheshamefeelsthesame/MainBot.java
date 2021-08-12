package alltheshamefeelsthesame;

import alltheshamefeelsthesame.services.bots.HandlerBot;
import alltheshamefeelsthesame.services.enums.Condition;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MainBot extends TelegramLongPollingBot {

    private final HandlerBot handlerBot = new HandlerBot(getBotToken(), getBotUsername());
    private Condition currentPage = Condition.FIRST_PAGE;

    @Override
    public String getBotToken() {
        return "1915985809:AAFKGeAE5s9S7oWm2sQwobwbqWvYfRCxsnc";
    }

    @Override
    public String getBotUsername() {
        return "ProgrammingHlpBot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
           currentPage = handlerBot.handle(currentPage, update.getMessage());
           return;
        }
        if (update.hasPreCheckoutQuery()) {
            handlerBot.request(update.getPreCheckoutQuery(), currentPage);
        }
    }
}
