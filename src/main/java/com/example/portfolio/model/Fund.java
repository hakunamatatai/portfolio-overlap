package com.example.portfolio.model;

import java.util.Set;

/**
 * @Description: Fund Entity
 * @Date 17/06/2025
 * @author evie
 */
public class Fund {
    /**
     * Fund Name
     */
    private String name;
    /**
     * Stock List of the fund
     */
    private Set<String> stocks;

    /**
     * constructor
     */
    public Fund(String name, Set<String> stocks) {
        this.name = name;
        this.stocks = stocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getStocks() {
        return stocks;
    }

    public void setStocks(Set<String> stocks) {
        this.stocks = stocks;
    }

    /**
     * get the number of stocks
     */
    public int getStockCount() {
        return stocks.size();
    }

    /**
     * add a new stock to stock set of current fund
     */
    public void addStock(String stock) {
        stocks.add(stock);
    }

    @Override
    public String toString() {
        return "Fund{name='" + name + "', stockCount=" + stocks.size() + "}";
    }
}
