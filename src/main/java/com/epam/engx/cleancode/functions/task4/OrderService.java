package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.List;

public class OrderService {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        return getOnlyAvailableProductsPrice();
    }

    private double getOnlyAvailableProductsPrice() {
        double orderPrice = 0.0D;
        for (Product product : products) {
            if (isAvailableProduct(product)) {
                orderPrice += product.getProductPrice();
            }
        }
        return orderPrice;
    }

    private boolean isAvailableProduct(Product product) {
        return product.isAvailable();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
