package alltheshamefeelsthesame.services;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class PageButtons {

    private final ReplyKeyboardMarkup[] keyboards = new ReplyKeyboardMarkup[10];

    {
        for (int i = 0; i < keyboards.length; i++) {
            keyboards[i] = new ReplyKeyboardMarkup();
            keyboards[i].setResizeKeyboard(true);
        }

        String[][][] but = {{{"Лабораторные работы", "Курсовые работы"}, {"Поддержка"}},
                {{"Основы информационной безопасности (политех)"}, {"Структуры данных", "Информатика"}, {"Назад"}},
                {{"Назад"}},
                {{"1", "2", "3", "4", "5", "6", "7"}, {"8", "9", "10", "11", "12", "13", "14"}, {"Назад", "Меню"}},
                {{"1", "2", "3", "4"}, {"Назад", "Меню"}},
                {{"1", "2", "3", "4", "5"}, {"Назад", "Меню"}},
                {{"1", "2", "3", "4", "5", "6", "7"}, {"Назад", "Меню"}},
                {{"1", "2", "3", "4"}, {"Назад", "Меню"}},
                {{"F + LZ", "F + A", "F + LZV", "F + H"}, {"A + LZW", "H + LZ", "A + H"}, {"Назад", "Меню"}},
                {{"CRC-16-IBM", "CRC-16-CCITT", "CRC-32C", "CRC-32Q"}, {"CRC-30", "CRC-32-IEEE 802.3", "CRC-32K"}, {"Назад", "Меню"}}};

        int pageNum = 0;
        for (String[][] strings : but) {
            createButtons(strings, pageNum++);
        }

    }

    private void createButtons(String[][] mem, int pageNum) {
        List<KeyboardRow> rows = new ArrayList<>();
        for (String[] strings : mem) {
            KeyboardRow row = new KeyboardRow();
            for (String string : strings) {
                row.add(string);
            }
            rows.add(row);
        }
        keyboards[pageNum].setKeyboard(rows);
    }

    public ReplyKeyboardMarkup getKeyboard(int page) {
        return keyboards[page];
    }


}
