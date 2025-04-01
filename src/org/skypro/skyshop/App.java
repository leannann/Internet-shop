package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {

    public static void main(String[] args) {
        SimpleProduct product1 = new SimpleProduct("Ноутбук", 90000);
        DiscountedProduct product2 = new DiscountedProduct("Смартфон", 100000, 20);
        FixPriceProduct product3 = new FixPriceProduct("Наушники");
        SimpleProduct product4 = new SimpleProduct("Часы", 22000);
        DiscountedProduct product5 = new DiscountedProduct("Зарядное устройство", 300, 5);
        SimpleProduct product6 = new SimpleProduct("Планшет", 40000);

        ProductBasket basket = new ProductBasket();

        // Добавление продукта в корзину
        System.out.println("Добавление продукта в корзину:");
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        // Добавление продукта в заполненную корзину в которой нет места
        System.out.println("Добавление продукта в заполненную корзину в которой нет места:");
        basket.addProduct(product6);

        // Печать содержимого корзины с несколькими товарами
        System.out.println("Печать содержимого корзины с несколькими товарами:");
        basket.printBasket();

        // Получение стоимости корзины с несколькими товарами:
        System.out.println("Получение стоимости корзины с несколькими товарами:");
        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        // Поиск товара, который есть в корзине
        System.out.println("Поиск товара, который есть в корзине:");
        System.out.println("Поиск ноутбука: " + basket.containsProduct("Ноутбук"));

        // Поиск товара, которого нет в корзине
        System.out.println("Поиск товара, которого нет в корзине:");
        System.out.println("Поиск планшета: " + basket.containsProduct("Планшет"));

        // Очистка корзины
        System.out.println("Очистка корзины:");
        basket.clearBasket();

        // Печать содержимого пустой корзины
        System.out.println("Печать содержимого пустой корзины:");
        basket.printBasket();

        // Получение стоимости пустой корзины
        System.out.println("Получение стоимости пустой корзины:");
        System.out.println("Общая стоимость после очистки: " + basket.getTotalPrice());

        // Поиск товара по имени в пустой корзине
        System.out.println("Поиск товара по имени в пустой корзине:");
        System.out.println("Поиск ноутбука после очистки: " + basket.containsProduct("Ноутбук"));

        // Создание поискового движка
        SearchEngine searchEngine = new SearchEngine(10);
        searchEngine.add(product1);
        searchEngine.add(product2);
        searchEngine.add(product3);
        searchEngine.add(product4);
        searchEngine.add(product5);

        // Добавление статей
        Article article1 = new Article("Обзор ноутбуков", "Как выбрать лучший ноутбук для работы и игр.");
        Article article2 = new Article("Сравнение смартфонов", "Смартфоны 2025 года: что выбрать?");
        searchEngine.add(article1);
        searchEngine.add(article2);

        // Демонстрация поиска
        System.out.println("Результаты поиска по 'ноутбук' (статья):");
        for (var result : searchEngine.search("ноутбук")) {
            if (result != null) {
                System.out.println(result.getStringRepresentation());
            }
        }
        System.out.println("Результаты поиска по 'смартфон' (статья):");
        for (var result : searchEngine.search("смартфон")) {
            if (result != null) {
                System.out.println(result.getStringRepresentation());
            }
        }

        System.out.println("Результаты поиска по 'Часы' (продукт):");
        for (var result : searchEngine.search("Часы")) {
            if (result != null) {
                System.out.println(result.getStringRepresentation());
            }
        }

        System.out.println("Результаты поиска по 'Наушники' (продукт):");
        for (var result : searchEngine.search("Наушники")) {
            if (result != null) {
                System.out.println(result.getStringRepresentation());
            }
        }

        // Демонстрация работы исключений
        System.out.println("Демонстрация работы исключений:");
        try {
            SimpleProduct product7 = new SimpleProduct("", 40000);
        } catch (Exception e) {
            System.out.println("Название продукта не может быть пустым или состоять только из пробелов");
        }

        try {
            SimpleProduct product8 = new SimpleProduct("      ", 40000);
        } catch (Exception e) {
            System.out.println("Название продукта не может быть пустым или состоять только из пробелов");
        }

        try {
            SimpleProduct product9 = new SimpleProduct("Планшет", 0);
        } catch (Exception e) {
            System.out.println("Цена продукта должна быть строго больше 0");
        }

        try {
            SimpleProduct product9 = new SimpleProduct("Планшет", -4535);
        } catch (Exception e) {
            System.out.println("Цена продукта должна быть строго больше 0");
        }

        try {
            DiscountedProduct product10 = new DiscountedProduct("Планшет", 45000, 110);
        } catch (Exception e) {
            System.out.println("Скидка должна быть в диапазоне от 0 до 100 включительно");
        }

        try {
            DiscountedProduct product10 = new DiscountedProduct("Планшет", 45000, -110);
        } catch (Exception e) {
            System.out.println("Скидка должна быть в диапазоне от 0 до 100 включительно");
        }

        // Демонстрация нового метода поиска
        System.out.println("Демонстрация нового метода поиска:");

        SearchEngine engine = new SearchEngine(5);
        engine.add(new SimpleProduct("молоко", 50));
        engine.add(new SimpleProduct("шоколад молочный", 100));
        engine.add(new DiscountedProduct("сыр", 200, 15));

        // Когда нужный объект существует
        System.out.println("когда нужный объект существует:");
        try {
            Searchable bestMatch = engine.findBestMatch("мол");
            System.out.println("Найден лучший результат: " + bestMatch);
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

        //  Поиск неудачен — нет подходящего объекта
        System.out.println("Поиск неудачен — нет подходящего объекта:");
        try {
            Searchable bestMatch = engine.findBestMatch("йогурт");
            System.out.println("Найден лучший результат: " + bestMatch);
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

    }
}
