package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> productsByName = new HashMap<>();;


    public void addProduct(Product product) {
        productsByName
                .computeIfAbsent(product.getProductName(), k -> new ArrayList<>())
                .add(product);
    }

    public int getTotalPrice() {
        int total = 0;
        for (List<Product> productList : productsByName.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }
        return total;
    }

    public void printBasket() {
        if (productsByName.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        int specialCount = 0;
        for (List<Product> productList : productsByName.values()) {
            for (Product product : productList) {
                System.out.println(product);
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean containsProduct(String name) {
        return productsByName.containsKey(name);
    }

    public void clearBasket() {
        productsByName.clear();
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = productsByName.remove(name);
        return removed != null ? removed : Collections.emptyList();
    }
}
