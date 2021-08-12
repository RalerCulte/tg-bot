package alltheshamefeelsthesame.services;

import alltheshamefeelsthesame.services.enums.Condition;

@FunctionalInterface
public interface Handler {

    public Condition handler(String message, String chatId);

}
