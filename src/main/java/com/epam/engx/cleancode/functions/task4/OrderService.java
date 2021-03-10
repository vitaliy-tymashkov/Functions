package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderService {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        List<Product> availableProducts = getAvailableProducts();
        return getAllProductsPrice(availableProducts);
    }

    private List<Product> getAvailableProducts() {
        List<Product> availableProducts = new ArrayList<>(products);
        Iterator<Product> iterator = availableProducts.iterator();
        while (iterator.hasNext()) {
            if (isNotAvailableProduct(iterator.next())) {
                iterator.remove();
            }
        }
        return availableProducts;
    }

    private double getAllProductsPrice(List<Product> availableProducts) {
        double orderPrice = 0.0D;
        for (Product product : availableProducts) {
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
