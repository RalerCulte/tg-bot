package alltheshamefeelsthesame.services.bots;

import alltheshamefeelsthesame.services.Files;
import alltheshamefeelsthesame.services.Handler;
import alltheshamefeelsthesame.services.enums.Condition;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;

import static alltheshamefeelsthesame.services.enums.Condition.*;
import static alltheshamefeelsthesame.services.enums.DocumentsTitles.*;
import static alltheshamefeelsthesame.services.enums.StickersTitles.*;
import static alltheshamefeelsthesame.services.enums.Subjects.*;

public class HandlerBot extends TelegramLongPollingBot {

    private final Files files = new Files();
    private final String adminId = "704162495";
    private final String[] seventhLabVars = {"F + LZ", "F + A", "F + LZV", "F + H", "A + LZW", "H + LZ", "A + H"};
    private final String[] ninthLabVars = {"CRC-16-IBM", "CRC-16-CCITT", "CRC-32C", "CRC-32Q", "CRC-30", "CRC-32-IEEE 802.3", "CRC-32K"};
    private final String fatherToken;
    private final String fatherBotUsername;
    private final ProductSenderBot productSender;
    private final SenderBot senderBot;
    private PaymentBot paymentBot;
    private final Handler[] handlers = {this::HandleFirstPage, this::HandleSecondPage, this::HandleThirdPage,
            this::HandleFourthPage, this::HandleFifthPage, this::HandleSixthPage, this::HandleSeventhPage,
            this::HandleEighthPage, this::HandleNinthPage, this::HandleTenthPage};


    public HandlerBot(String token, String botUsername) {
        fatherToken = token;
        fatherBotUsername = botUsername;
        senderBot = new SenderBot(fatherToken, fatherBotUsername);
        productSender = new ProductSenderBot(fatherToken, fatherBotUsername);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Updates handling at MainBot
    }

    @Override
    public String getBotToken() {
        return fatherToken;
    }

    @Override
    public String getBotUsername() {
        return fatherBotUsername;
    }

    public Condition handle(Condition currentPage, Message message) {

        if (message.hasSuccessfulPayment()) {
            senderBot.sendText(adminId, "Оплата получена", FIRST_PAGE);
            senderBot.sendText(message.getChatId().toString(), "Спасибо за покупку!\nБудем благодарны отзыву тут: https://vk.com/topic-206190730_48122282",
                    FIRST_PAGE);
            switch (productSender.payDetails.get(message.getChatId().toString())[0]) {
                case 0 -> senderBot.sendSticker(message.getChatId().toString(), files.getSticker(OIB_BOUGHT.ordinal()), FIRST_PAGE);
                case 1 -> senderBot.sendSticker(message.getChatId().toString(), files.getSticker(DS_BOUGHT.ordinal()), FIRST_PAGE);
                case 2 -> senderBot.sendSticker(message.getChatId().toString(), files.getSticker(INF_BOUGHT.ordinal()), FIRST_PAGE);
            }
            return FIRST_PAGE;
        }

        return handlers[currentPage.ordinal()].handler(message.getText(), message.getChatId().toString());
    }

    public void noMatches(String message, String chatId, Condition condition) {
        if (message.contains("/start")) {
            senderBot.sendText(chatId, "Для навигации используйте кнопки", FIRST_PAGE);
        } else {
            handleAutism(message, condition, chatId);
        }
    }

    private void handleAutism(String message, Condition condition, String chatId) {

        String[] checkShit = {"хуй", "пизд", "пидор", "гей", "пету", "бля",
                "мать", "сдох", "соси", "нюхай", "жопа", "говно", "чел"};
        boolean check = false;
        for (String s : checkShit) {
            if (message.toLowerCase().contains(s)) {
                check = true;
                break;
            }
        }

        if (check) {
            senderBot.sendSticker(chatId, files.getSticker(AUTISM_FOUND.ordinal()), condition);
        } else {
            senderBot.sendText(chatId, "Я не понимаю \uD83E\uDD2F", condition);
        }

    }

    public void request(PreCheckoutQuery query, Condition condition) {
        PaymentBot payRequest = new PaymentBot(getBotToken(), getBotUsername(), 0);
        payRequest.handleQuery(query);
        senderBot.sendText(adminId, "Попытка покупки", condition);
    }

    private Condition checkBack(String message, Condition condition, String chatId) {
        if (message.equals("Меню")) {
            senderBot.sendText(chatId, "Для навигации используйте кнопки", FIRST_PAGE);
            return FIRST_PAGE;
        } else if (message.equals("Назад")) {
            if (condition.ordinal() < 6) {
                senderBot.sendText(chatId, "Выберите категорию", SECOND_PAGE);
                return SECOND_PAGE;
            } else {
                senderBot.sendText(chatId, "Выберите номер работы, которая вас интересует", FOURTH_PAGE);
                return FOURTH_PAGE;
            }
        }
        return null;
    }

    private boolean findSame(String message, String chatId, int i) {
        if (message.equals(Integer.toString(i + 1))) {
            productSender.variant.put(chatId, String.valueOf(i));
            paymentBot = new PaymentBot(getBotToken(), getBotUsername(), productSender.payDetails.get(chatId)[1]);
            paymentBot.sendInvoice(OIB, chatId, String.valueOf(i + 1));
            return true;
        }
        return false;
    }

    private boolean findSameWords(String message, String chatId, Condition condition) {
        String[] find;
        if (condition == NINTH_PAGE) {
            find = seventhLabVars;
        } else {
            find = ninthLabVars;
        }

        for (String s : find) {
            if (s.equals(message)) {
                productSender.variant.put(chatId, message);
                paymentBot = new PaymentBot(getBotToken(), getBotUsername(), productSender.payDetails.get(chatId)[1]);
                paymentBot.sendInvoice(OIB, chatId, message);
                return true;
            }
        }
        return false;
    }

    private Condition HandleFirstPage(String message, String chatId) {
        switch (message) {
            case "Лабораторные работы" -> {
                senderBot.sendText(chatId, "Выберите категорию", SECOND_PAGE);
                return SECOND_PAGE;
            }
            case "Курсовые работы" -> {
                senderBot.sendText(chatId, """
                                К сожалению, мы сейчас автоматизированно не продаем курсовые работы.
                                Цена и сроки обсуждаются индивидуально. Для того, чтобы заказать выполнение курсовой работы пишите сюда:
                                https://vk.me/programminghlp"""
                        , THIRD_PAGE);
                return THIRD_PAGE;
            }
            case "Поддержка" -> {
                senderBot.sendText(chatId, "Если у вас возникли какие-либо проблемы или есть вопросы, свяжитесь с нашим сотрудником\nhttps://t.me/alltheshamefeelsthesame",
                        THIRD_PAGE);
                senderBot.sendSticker(chatId, files.getSticker(CONTACTS.ordinal()), THIRD_PAGE);
                return Condition.THIRD_PAGE;
            }
        }

        noMatches(message, chatId, FIRST_PAGE);
        return FIRST_PAGE;
    }

    private Condition HandleSecondPage(String message, String chatId) {
        switch (message) {

            case "Основы информационной безопасности (политех)" -> {
                senderBot.sendText(chatId, """
                        Все лабораторные работы из методического пособия.
                        Номера совпадают с порядком в методичке, работы №2, №6, №7, №9 имеют различные варианты, будьте внимательней.
                                                
                        Архив содержит методичку и варианты, можно проверить, совпадают ли наши варианты с вашими.
                                                
                        Выберите номер работы, которая вас интересует
                        """, FOURTH_PAGE);
                senderBot.sendDocument(chatId, files.getDocument(OIB_LABS.ordinal()));
                return FOURTH_PAGE;
            }
            case "Структуры данных" -> {
                senderBot.sendText(chatId, """
                        Файл содержит условия решенных работ.
                        Номер для покупки совпадает с номером в файле.
                                                
                        Выберите номер работы, которая вас интересует
                        """, FIFTH_PAGE);
                senderBot.sendDocument(chatId, files.getDocument(DS_LABS.ordinal()));
                return FIFTH_PAGE;
            }
            case "Информатика" -> {
                senderBot.sendText(chatId, """
                        Файл содержит условия решенных работ.
                        Номер для покупки совпадает с номером в файле.
                                                
                        Выберите номер работы, которая вас интересует
                        """, SIXTH_PAGE);
                senderBot.sendDocument(chatId, files.getDocument(INF_LABS.ordinal()));
                return SIXTH_PAGE;
            }
            case "Назад" -> {
                senderBot.sendText(chatId, "Для навигации используйте кнопки", FIRST_PAGE);
                return FIRST_PAGE;
            }
        }

        noMatches(message, chatId, SECOND_PAGE);
        return SECOND_PAGE;
    }

    private Condition HandleThirdPage(String message, String chatId) {

        if (message.equals("Назад")) {
            senderBot.sendText(chatId, "Для навигации используйте кнопки", FIRST_PAGE);
            return FIRST_PAGE;
        }

        noMatches(message, chatId, THIRD_PAGE);
        return THIRD_PAGE;
    }

    private Condition HandleFourthPage(String message, String chatId) {
        Condition check = checkBack(message, FOURTH_PAGE, chatId);
        if (check != null) {
            return check;
        }
        for (int i = 0; i < 14; i++) {
            if (message.equals(Integer.toString(i + 1))) {
                switch (i) {
                    case 1 -> {
                        senderBot.sendText(chatId, "Выберите вариант", SEVENTH_PAGE);
                        productSender.payDetails.put(chatId, new int[]{0, i});
                        return SEVENTH_PAGE;
                    }
                    case 5 -> {
                        senderBot.sendText(chatId, "Выберите вариант", EIGHTH_PAGE);
                        productSender.payDetails.put(chatId, new int[]{0, i});
                        return EIGHTH_PAGE;
                    }
                    case 6 -> {
                        senderBot.sendText(chatId, "Выберите вариант", NINTH_PAGE);
                        productSender.payDetails.put(chatId, new int[]{0, i});
                        return NINTH_PAGE;
                    }
                    case 8 -> {
                        senderBot.sendText(chatId, "Выберите вариант", TENTH_PAGE);
                        productSender.payDetails.put(chatId, new int[]{0, i});
                        return TENTH_PAGE;
                    }
                }
                paymentBot = new PaymentBot(getBotToken(), getBotUsername(), i);
                paymentBot.sendInvoice(OIB, chatId, null);
                productSender.payDetails.put(chatId, new int[]{0, i});

                return FOURTH_PAGE;
            }
        }

        noMatches(message, chatId, FOURTH_PAGE);
        return FOURTH_PAGE;
    }

    private Condition HandleFifthPage(String message, String chatId) {
        Condition check = checkBack(message, FIFTH_PAGE, chatId);
        if (check != null) {
            return check;
        }
        for (int i = 0; i < 4; i++) {
            if (message.equals(Integer.toString(i + 1))) {
                paymentBot = new PaymentBot(getBotToken(), getBotUsername(), i);
                paymentBot.sendInvoice(DATA_STRUCTURES, chatId, null);
                productSender.payDetails.put(chatId, new int[]{1, i});
                return FIFTH_PAGE;
            }
        }
        noMatches(message, chatId, FIFTH_PAGE);
        return FIFTH_PAGE;
    }

    private Condition HandleSixthPage(String message, String chatId) {
        Condition check = checkBack(message, SIXTH_PAGE, chatId);
        if (check != null) {
            return check;
        }
        for (int i = 0; i < 5; i++) {
            if (message.equals(Integer.toString(i + 1))) {
                paymentBot = new PaymentBot(getBotToken(), getBotUsername(), i);
                paymentBot.sendInvoice(INF, chatId, null);
                productSender.payDetails.put(chatId, new int[]{2, i});
                return SIXTH_PAGE;
            }
        }
        noMatches(message, chatId, SIXTH_PAGE);
        return SIXTH_PAGE;
    }

    private Condition HandleSeventhPage(String message, String chatId) {
        Condition check = checkBack(message, SEVENTH_PAGE, chatId);
        if (check != null) {
            return check;
        }
        for (int i = 0; i < 7; i++) {
            if (findSame(message, chatId, i)) return SEVENTH_PAGE;
        }
        noMatches(message,chatId, SEVENTH_PAGE);
        return SEVENTH_PAGE;
    }

    private Condition HandleEighthPage(String message, String chatId) {
        Condition check = checkBack(message, EIGHTH_PAGE, chatId);
        if (check != null) {
            return check;
        }
        for (int i = 0; i < 4; i++) {
            if (findSame(message, chatId, i)) return EIGHTH_PAGE;
        }
        noMatches(message,chatId, EIGHTH_PAGE);
        return EIGHTH_PAGE;
    }

    private Condition HandleNinthPage(String message, String chatId) {
        Condition check = checkBack(message, EIGHTH_PAGE, chatId);
        if (check != null) {
            return check;
        }
        if (findSameWords(message, chatId, NINTH_PAGE)) return NINTH_PAGE;
        noMatches(message,chatId, NINTH_PAGE);
        return NINTH_PAGE;
    }

    private Condition HandleTenthPage(String message, String chatId) {
        Condition check = checkBack(message, EIGHTH_PAGE, chatId);
        if (check != null) {
            return check;
        }
        if (findSameWords(message, chatId, TENTH_PAGE)) return TENTH_PAGE;
        noMatches(message,chatId, TENTH_PAGE);
        return TENTH_PAGE;
    }


}
