package com.wran.cantor.controller;

import com.wran.cantor.dto.ExchangeRatesDashboardDto;
import com.wran.cantor.dto.TransactionDashboardDto;
import com.wran.cantor.dto.WalletRatesDashboardDto;
import com.wran.cantor.model.ExchangeRates;
import com.wran.cantor.model.Transaction;
import com.wran.cantor.model.User;
import com.wran.cantor.service.CurrenciesService;
import com.wran.cantor.service.DtoConverterService;
import com.wran.cantor.service.TransactionService;
import com.wran.cantor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private CurrenciesService currenciesService;

    @Autowired
    private DtoConverterService converterService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/")
    public String dashboardPage(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("wallet", converterService.convertToDashboardDto(user.getWallet()));
        model.addAttribute("rates", converterService.convertToDashboardDto(currenciesService.getNewestExchangeRates()));

        return "dashboard";
    }
    @GetMapping("/dashboard/transactionData")
    @ResponseBody
    public WalletRatesDashboardDto getRequiredTransactionData(Principal principal){
        User user = userService.findByUsername(principal.getName());
        return new WalletRatesDashboardDto(converterService.convertToDashboardDto(user.getWallet()),
                converterService.convertToDashboardDto(currenciesService.getNewestExchangeRates()));
    }

    @GetMapping("/dashboard/latestRates")
    @ResponseBody
    public List<ExchangeRatesDashboardDto> getLatestRates(){
        List<ExchangeRates> latestRates = currenciesService.getLatest20ExchangeRates();
        return converterService.convertToDashboardDto(latestRates);
    }

    @PostMapping("/dashboard/transaction")
    public ResponseEntity<String> processTransaction(@RequestBody TransactionDashboardDto transactionDto,
                                                     Principal principal){
        User user = userService.findByUsername(principal.getName());
        Transaction transaction = converterService.convertFromDashboardDto(transactionDto);
        transaction.setUser(user);
        transactionService.save(transaction);

        //BAD REQUEST

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
