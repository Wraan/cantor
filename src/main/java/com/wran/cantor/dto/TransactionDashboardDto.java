package com.wran.cantor.dto;

import java.io.Serializable;

public class TransactionDashboardDto implements Serializable {

    private String action;
    private String code;
    private int amount;

    public TransactionDashboardDto() {
    }

    public TransactionDashboardDto(String action, String code, int amount) {
        this.action = action;
        this.code = code;
        this.amount = amount;
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
}
