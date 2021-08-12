package alltheshamefeelsthesame.services.bots;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class ProductSenderBot extends TelegramLongPollingBot {

    private final String fatherToken;
    private final String fatherBotUsername;
    public final HashMap<String, int[]> payDetails = new HashMap<>();
    public final HashMap<String, String> variant = new HashMap<>();
    private final int[][] labsArray = {
            // OIB
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14},
            // STRUCTURE
            {1, 2, 3, 4},
            // INF
            {1, 2, 3, 4, 5}
    };

    public ProductSenderBot (String token, String botUsername) {
        fatherToken = token;
        fatherBotUsername = botUsername;
    }

    @Override
    public String getBotToken() {
        return fatherToken;
    }


    @Override
    public String getBotUsername() {
        return fatherBotUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //Updates handling at MainBot
    }


}
