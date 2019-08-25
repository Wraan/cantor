package com.wran.cantor.dto;

import java.io.Serializable;

public class WalletRatesDashboardDto implements Serializable {

    private WalletDashboardDto wallet;
    private WalletDashboardDto cantorWallet;
    private ExchangeRatesDashboardDto rates;


    public WalletRatesDashboardDto() {
    }

    public WalletRatesDashboardDto(WalletDashboardDto wallet, WalletDashboardDto cantorWallet, ExchangeRatesDashboardDto rates) {
        this.wallet = wallet;
        this.cantorWallet = cantorWallet;
        this.rates = rates;
    }

    public WalletDashboardDto getWallet() {
        return wallet;
    }

    public void setWallet(WalletDashboardDto wallet) {
        this.wallet = wallet;
    }

    public ExchangeRatesDashboardDto getRates() {
        return rates;
    }

    public void setRates(ExchangeRatesDashboardDto rates) {
        this.rates = rates;
    }

    public WalletDashboardDto getCantorWallet() {
        return cantorWallet;
    }

    public void setCantorWallet(WalletDashboardDto cantorWallet) {
        this.cantorWallet = cantorWallet;
    }
}
