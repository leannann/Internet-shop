package org.skypro.skyshop.search;

public class BestResultNotFound extends Exception{
    public BestResultNotFound(String searchQuery) {
        super("Не найдено подходящего результата для запроса: " + searchQuery);
    }
}
