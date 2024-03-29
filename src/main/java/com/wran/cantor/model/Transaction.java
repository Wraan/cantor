package com.wran.cantor.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String code;
    private int unit;
    private int amount;
    private Date date;
    private double rate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Transaction() {
    }

    public Transaction(String action, String code, int amount, int unit, double rate, Date date, User user) {
        this.action = action;
        this.code = code;
        this.amount = amount;
        this.unit = unit;
        this.date = date;
        this.rate = rate;
        this.user = user;
    }

    public Transaction(String action, String code, int amount, int unit, double rate, Date date) {
        this.action = action;
        this.code = code;
        this.amount = amount;
        this.unit = unit;
        this.date = date;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
