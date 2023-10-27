package org.Jag.Models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {

    private int id;
    private Date date;
    //    private User user;
    private ArrayList<Cart> cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public ArrayList<Cart> getCartProducts() {
        return cart;
    }

    public void setCartProducts(ArrayList<Cart> cart) {
        this.cart = cart;
    }
}
