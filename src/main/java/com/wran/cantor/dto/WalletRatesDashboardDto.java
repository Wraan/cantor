package com.wran.cantor.dto;

import java.io.Serializable;

public class WalletRatesDashboardDto implements Serializable {

    private WalletDashboardDto wallet;
    private ExchangeRatesDashboardDto rates;

    public WalletRatesDashboardDto() {
    }

    public WalletRatesDashboardDto(WalletDashboardDto wallet, ExchangeRatesDashboardDto rates) {
        this.wallet = wallet;
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
}
