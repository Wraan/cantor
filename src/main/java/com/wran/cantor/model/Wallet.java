package com.wran.cantor.model;

import javax.persistence.*;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float funds;
    private int usdAmount;
    private int eurAmount;
    private int chfAmount;
    private int rubAmount;
    private int czkAmount;
    private int gbpAmount;

    public Wallet() {
    }

    public Wallet(float funds, int usdAmount, int eurAmount, int chfAmount, int rubAmount,
                  int czkAmount, int gbpAmount) {
        this.funds = funds;
        this.usdAmount = usdAmount;
        this.eurAmount = eurAmount;
        this.chfAmount = chfAmount;
        this.rubAmount = rubAmount;
        this.czkAmount = czkAmount;
        this.gbpAmount = gbpAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getFunds() {
        return funds;
    }

    public void setFunds(float funds) {
        this.funds = funds;
    }

    public int getUsdAmount() {
        return usdAmount;
    }

    public void setUsdAmount(int usdAmount) {
        this.usdAmount = usdAmount;
    }

    public int getEurAmount() {
        return eurAmount;
    }

    public void setEurAmount(int eurAmount) {
        this.eurAmount = eurAmount;
    }

    public int getChfAmount() {
        return chfAmount;
    }

    public void setChfAmount(int chfAmount) {
        this.chfAmount = chfAmount;
    }

    public int getRubAmount() {
        return rubAmount;
    }

    public void setRubAmount(int rubAmount) {
        this.rubAmount = rubAmount;
    }

    public int getCzkAmount() {
        return czkAmount;
    }

    public void setCzkAmount(int czkAmount) {
        this.czkAmount = czkAmount;
    }

    public int getGbpAmount() {
        return gbpAmount;
    }

    public void setGbpAmount(int gbpAmount) {
        this.gbpAmount = gbpAmount;
    }
}