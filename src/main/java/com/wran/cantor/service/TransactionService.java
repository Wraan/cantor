package com.wran.cantor.service;

import com.wran.cantor.dto.WalletDashboardDto;
import com.wran.cantor.model.CurrencyRates;
import com.wran.cantor.model.Transaction;
import com.wran.cantor.model.User;
import com.wran.cantor.model.Wallet;
import com.wran.cantor.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    private final static String CANTOR_USERNAME = "cantor";

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Wallet getCantorWallet() {
        User cantor = userService.findByUsername("cantor");
        if(cantor != null)
            return cantor.getWallet();
        return null;
    }

    public boolean exchangeWithCantor(Transaction transaction) {
        User cantor = userService.findByUsername(CANTOR_USERNAME);
        User user = transaction.getUser();

        User buyer;
        User seller;
        if(transaction.getAction().equals("BUY")){
            buyer = user;
            seller = cantor;
        }
        else {
            buyer = cantor;
            seller = user;
        }
        Wallet buyerWallet = buyer.getWallet();
        Wallet sellerWallet = seller.getWallet();

        BigDecimal cost = new BigDecimal(Double.toString(transaction.getAmount()))
                .multiply(new BigDecimal(Double.toString(transaction.getRate())))
                .divide(new BigDecimal(Integer.toString(transaction.getUnit())));

        if(!exchangeFunds(buyerWallet, sellerWallet, cost)) return false;

        switch (transaction.getCode()){
            case "USD": if(!exchangeUSD(buyerWallet, sellerWallet, transaction.getAmount())) return false; break;
            case "EUR": if(!exchangeEUR(buyerWallet, sellerWallet, transaction.getAmount())) return false; break;
            case "CHF": if(!exchangeCHF(buyerWallet, sellerWallet, transaction.getAmount())) return false; break;
            case "RUB": if(!exchangeRUB(buyerWallet, sellerWallet, transaction.getAmount())) return false; break;
            case "CZK": if(!exchangeCZK(buyerWallet, sellerWallet, transaction.getAmount())) return false; break;
            case "GBP": if(!exchangeGBP(buyerWallet, sellerWallet, transaction.getAmount())) return false; break;
            default: return false;
        }
        userService.saveAll(Arrays.asList(user, cantor));
        return true;
    }
    private boolean exchangeFunds(Wallet buyer, Wallet seller, BigDecimal amount)   {
        if(buyer.getFunds().compareTo(amount) < 0)
            return false;
        buyer.setFunds(buyer.getFunds().subtract(amount));
        seller.setFunds(seller.getFunds().add(amount));
        return true;
    }
    private boolean exchangeUSD(Wallet buyer, Wallet seller, int amount){
        if(seller.getUsdAmount() < amount)
            return false;
        seller.setUsdAmount(seller.getUsdAmount() - amount);
        buyer.setUsdAmount(buyer.getUsdAmount() + amount);
        return true;
    }
    private boolean exchangeEUR(Wallet buyer, Wallet seller, int amount){
        if(seller.getEurAmount() < amount)
            return false;
        seller.setEurAmount(seller.getEurAmount() - amount);
        buyer.setEurAmount(buyer.getEurAmount() + amount);
        return true;
    }
    private boolean exchangeCHF(Wallet buyer, Wallet seller, int amount){
        if(seller.getChfAmount() < amount)
            return false;
        seller.setChfAmount(seller.getChfAmount() - amount);
        buyer.setChfAmount(buyer.getChfAmount() + amount);
        return true;
    }
    private boolean exchangeRUB(Wallet buyer, Wallet seller, int amount){
        if(seller.getRubAmount() < amount)
            return false;
        seller.setRubAmount(seller.getRubAmount() - amount);
        buyer.setRubAmount(buyer.getRubAmount() + amount);
        return true;
    }
    private boolean exchangeCZK(Wallet buyer, Wallet seller, int amount){
        if(seller.getCzkAmount() < amount)
            return false;
        seller.setCzkAmount(seller.getCzkAmount() - amount);
        buyer.setCzkAmount(buyer.getCzkAmount() + amount);
        return true;
    }
    private boolean exchangeGBP(Wallet buyer, Wallet seller, int amount){
        if(seller.getGbpAmount() < amount)
            return false;
        seller.setGbpAmount(seller.getGbpAmount() - amount);
        buyer.setGbpAmount(buyer.getGbpAmount() + amount);
        return true;
    }

}
