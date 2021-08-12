package alltheshamefeelsthesame.services.bots;

import alltheshamefeelsthesame.services.enums.Subjects;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;

public class PaymentBot extends TelegramLongPollingBot {

    private final String fatherToken;
    private final String fatherBotUsername;
    private final int labNumber;
    private static final String adminId = "704162495";
    private static final String[] titles = {"Основы ИнфоБеза", "Структуры данных", "Информатика"};

    private static final String[] oibTitles = {
            "#1 Математические примитивы криптографии",
            "#2 Основы частотного криптоанализа",
            "#3 Изучение программных уязвимостей типа \"переполнение буфера\"",
            "#4 Защита от встраиваемых потайных ходов",
            "#5 Анализ вредоносных программных средств",
            "#6 Защита программного обеспечения от нелегального использования",
            "#7 Кодирование и упаковка данных",
            "#8 Основы стеганографической защиты информации",
            "#9 Методы контроля целостности",
            "#10 Методы надежного хранения и передачи информации",
            "#11 Механизм аутентификации пользователей",
            "#12 Система контроля доступа",
            "#13 Защита web-сервера от несанкционированного доступа",
            "#14 3ащита от угроз нарушения безопасности типа \"отказ в обслуживании\"",
    };
    private static final String[] structuresTitles = {
            "Изучение свойств линейного конгруэнтного генератора псевдослучайных чисел",
            "Работа с файлами",
            "Калькулятор на основе обратной польской записи",
            "Реализация базы данных"
    };
    private static final String[] infTitles = {
            "#1 Телефонный справочник",
            "#2 Хранение типов данных в языке Си",
            "#3 Длинная арифметика",
            "#4 Палиндром",
            "#5 Сортировки"
    };
    private static final String[][] allTitles = {oibTitles, structuresTitles, infTitles};


    public PaymentBot (String token, String botUsername, int labNumber) {
        fatherToken = token;
        fatherBotUsername = botUsername;
        this.labNumber = labNumber;
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

    @SneakyThrows
    public void sendInvoice(Subjects subject, String chatId, String variant) {

        String description;
        if (variant == null) {
            description = allTitles[subject.ordinal()][labNumber];
        } else {
            description = allTitles[subject.ordinal()][labNumber] + ", вариант " + variant;
        }

        execute(SendInvoice.builder()
                .chatId(chatId)
                .title(titles[subject.ordinal()])
                .description(description)
                .payload("payload")
                .providerToken("401643678:TEST:3947e67a-cf1f-4db4-967c-1beae96df73d")
                .currency("rub")
                .startParameter("start")
                .photoUrl("https://wallpaperaccess.com/full/5234736.png")
                .photoHeight(256)
                .photoWidth(350)
                .price(new LabeledPrice("Лабораторная работа (" + titles[subject.ordinal()] + ")", getPrice(subject)))
                .needEmail(true)
                .needName(true)
                .build());
    }

    private int getPrice(Subjects subject) {
        if (subject == Subjects.DATA_STRUCTURES) {
            return 150000;
        }
        return 100000;
    }

    @SneakyThrows
    public void handleQuery(PreCheckoutQuery query) {
        execute(AnswerPreCheckoutQuery.builder()
                .preCheckoutQueryId(query.getId())
                .ok(true)
                .build());
        execute(SendMessage.builder()
                .text(query.getOrderInfo().getName() + "\n" + query.getOrderInfo().getEmail())
                .chatId(adminId)
                .build());
    }

}
