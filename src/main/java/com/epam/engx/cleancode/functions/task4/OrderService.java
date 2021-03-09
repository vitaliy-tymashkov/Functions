package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.Iterator;
import java.util.List;

public class OrderService {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        removeNotAvailableProducts();
        return getAllProductsPrice();
    }

    private void removeNotAvailableProducts() {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            if (isNotAvailableProduct(iterator.next())) {
                iterator.remove();
            }
        }
    }

    private double getAllProductsPrice() {
        double orderPrice = 0.0D;
        for (Product product : products) {
            orderPrice += product.getProductPrice();
        }
        return orderPrice;
    }

    private boolean isNotAvailableProduct(Product product) {
        return !product.isAvailable();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
