package com.wran.cantor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RegisterUserForm implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    private float funds;
    @JsonProperty("usd")
    private int usdAmount;
    @JsonProperty("eur")
    private int eurAmount;
    @JsonProperty("chf")
    private int chfAmount;
    @JsonProperty("rub")
    private int rubAmount;
    @JsonProperty("czk")
    private int czkAmount;
    @JsonProperty("gbp")
    private int gbpAmount;

    public RegisterUserForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public float getFunds() {
        return funds;
    }

    public void setFunds(float funds) {
        this.funds = funds;
    }
}
