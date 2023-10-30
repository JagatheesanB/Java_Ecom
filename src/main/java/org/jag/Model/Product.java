package org.jag.Model;

import org.jag.utils.AppExecption;

public class Product {
    private int id;
    private String title;
    private double price;
    private int stocks;
    private Category category;

    public Product(int id, String title,
                   double price, int stocks, Category category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stocks = stocks;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws AppExecption {
        if (price <= 0) {
            throw new AppExecption("Price Cannot be negative");
        }
        this.price = price;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}