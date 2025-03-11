package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;

public class App {

    public static void main(String[] args) {
        Product product1 = new Product("Ноутбук", 90000);
        Product product2 = new Product("Смартфон", 100000);
        Product product3 = new Product("Наушники", 2000);
        Product product4 = new Product("Часы", 22000);
        Product product5 = new Product("Зарядное устройство", 300);
        Product product6 = new Product("Планшет", 40000);

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
        System.out.println("    Получение стоимости корзины с несколькими товарами:");
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
    }
}
