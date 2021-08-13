package alltheshamefeelsthesame.services.bots;

import alltheshamefeelsthesame.services.Files;
import alltheshamefeelsthesame.services.InputFileWithVariants;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Objects;

public class ProductSenderBot extends TelegramLongPollingBot {

    private final String fatherToken;
    private final String fatherBotUsername;
    Files files = new Files();
    public final HashMap<String, int[]> payDetails = new HashMap<>();
    public final HashMap<String, String> variant = new HashMap<>();

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

    @SneakyThrows
    public void sendProduct(String chatId) {
        InputFile document = null;
        switch (payDetails.get(chatId)[0]) {
            case 0 -> {
                int check = payDetails.get(chatId)[1];
                if (check == 1 || check == 5 || check == 6 || check == 8) {
                    InputFileWithVariants var = (InputFileWithVariants) files.getOibLab(check);
                    document = var.getVariant(check);
                }
            }
            case 1 -> document = files.getDataStructuresLab(payDetails.get(chatId)[1]);
            case 2 -> document = files.getInfLab(payDetails.get(chatId)[1]);
        }
        execute(SendDocument.builder()
                .chatId(chatId)
                .document(Objects.requireNonNull(document))
                .build());
    }


}
