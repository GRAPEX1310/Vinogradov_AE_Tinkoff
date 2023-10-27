package edu.hw3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Task6 {
    static class StockMarket {

        private PriorityQueue<Stock> stockPriorityQueue = new PriorityQueue<>(new StockComparator());

        void add(Stock stock) {
            stockPriorityQueue.add(stock);
        }

        void remove(Stock stock) {
            stockPriorityQueue.remove(stock);
        }

        Stock mostValuableStock() {
            return stockPriorityQueue.peek();
        }
    }

    static class Stock {

        private String name;
        private int cost;

        Stock() {
            name = "";
            cost = 0;
        }

        Stock(String givenName, int givenCost) {
            name = givenName;
            cost = givenCost;
        }

        public String getName() {
            return name;
        }

        public int getCost() {
            return cost;
        }
    }

    static class StockComparator implements Comparator<Stock> {
        @Override
        public int compare(Stock firstStock, Stock secondStock) {
            int value = Double.compare(firstStock.cost, secondStock.cost);

            if (value > 0) {
                return -1;
            } else if (value < 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
