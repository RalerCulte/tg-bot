package alltheshamefeelsthesame.services.bots;

import alltheshamefeelsthesame.services.PageButtons;
import alltheshamefeelsthesame.services.enums.Condition;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SenderBot extends TelegramLongPollingBot {

    private final String fatherToken;
    private final String fatherBotUsername;
    private final PageButtons buttons = new PageButtons();

    public SenderBot(String token, String botUsername) {
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
        // Updates handling at MainBot
    }

    @SneakyThrows
    public void sendText(String id, String text, Condition condition) {
        execute(SendMessage.builder()
                .chatId(id)
                .text(text)
                .replyMarkup(buttons.getKeyboard(condition.ordinal()))
                .build());
    }

    @SneakyThrows
    public void sendDocument(String id, InputFile document) {
        execute(SendDocument.builder()
                .chatId(id)
                .document(document)
                .build());
    }

    @SneakyThrows
    public void sendSticker(String id, InputFile sticker, Condition condition) {
        execute(SendSticker.builder()
                .chatId(id)
                .sticker(sticker)
                .replyMarkup(buttons.getKeyboard(condition.ordinal()))
                .build());
    }

}
