package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.stubs.AvailableProductStub;
import com.epam.engx.cleancode.functions.task4.stubs.UnavailableProductStub;
import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {

    private static final double DELTA = 0.0001D;
    private static final double EXPECTED_0 = 0.0;
    private static final double EXPECTED_20 = 20.0;

    private OrderService orderService = new OrderService();

    @Test
    public void shouldGetProducts() {
        ArrayList<Product> products = getList(new AvailableProductStub(), new AvailableProductStub());
        orderService.setProducts(products);
        assertEquals(products, orderService.getProducts());
    }

    @Test
    public void shouldCalculateZeroIfOrderContainsNoProduct() {
        orderService.setProducts(new ArrayList<Product>());
        assertEquals(EXPECTED_0, orderService.getPriceOfAvailableProducts(), DELTA);
    }

    @Test
    public void shouldCalculateZeroIfOrderContainsOnlyUnavailableProducts() {
        ArrayList<Product> products = getList(
                new UnavailableProductStub(),
                new UnavailableProductStub());
        orderService.setProducts(products);
        assertEquals(EXPECTED_0, orderService.getPriceOfAvailableProducts(), DELTA);
    }

    @Test
    public void shouldCalculateTwentyIfOrderContainsTwoAvailable10PriceProducts() {
        ArrayList<Product> products = getList(
                new AvailableProductStub(),
                new AvailableProductStub());
        orderService.setProducts(products);
        assertEquals(EXPECTED_20, orderService.getPriceOfAvailableProducts(), DELTA);
    }

    @Test
    public void shouldCalculateTwentyIfOrderContainsTwoAvailable10PriceProductsWithOtherUnavailableProducts() {
        ArrayList<Product> products = getList(
                new UnavailableProductStub(),
                new AvailableProductStub(),
                new AvailableProductStub(),
                new UnavailableProductStub());
        orderService.setProducts(products);
        assertEquals(EXPECTED_20, orderService.getPriceOfAvailableProducts(), DELTA);
    }

    @Test
    public void shouldCalculateTwentyIfOrderContainsTwoAvailable10PriceProductsInTheBeginningWithOtherUnavailableProducts() {
        ArrayList<Product> products = getList(
                new AvailableProductStub(),
                new AvailableProductStub(),
                new UnavailableProductStub(),
                new UnavailableProductStub());
        orderService.setProducts(products);
        assertEquals(EXPECTED_20, orderService.getPriceOfAvailableProducts(), DELTA);
    }

    private ArrayList<Product> getList(Product... products) {
        return new ArrayList<>(Arrays.asList(products));
    }
}
