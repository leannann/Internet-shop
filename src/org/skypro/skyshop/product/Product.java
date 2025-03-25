package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {

    private final String productName;

    public Product(String productName) {
        if (productName == null || productName.isBlank())
            throw new IllegalArgumentException("Название продукта не может быть пустым или состоять только из пробелов");
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public abstract int getPrice();
    public abstract boolean isSpecial();

    @Override
    public String getSearchTerm() {
        return productName;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getName(){
        return  productName;
    }

    @Override
    public abstract String toString();
}
