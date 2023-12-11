package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    @DisplayName("Testing Stock Class")
    void testStock() {
        Task6.StockMarket stockMarket = new Task6.StockMarket();

        Task6.Stock stock1 = new Task6.Stock("stock1", 100);
        Task6.Stock stock2 = new Task6.Stock("stock2", 200);
        Task6.Stock stock3 = new Task6.Stock("stock3", 300);

        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        assertThat(stockMarket.mostValuableStock().getName()).isEqualTo("stock3");

        stockMarket.remove(stockMarket.mostValuableStock());

        assertThat(stockMarket.mostValuableStock().getName()).isEqualTo("stock2");

        stockMarket.remove(stock1);

        assertThat(stockMarket.mostValuableStock().getName()).isEqualTo("stock2");

    }
}
