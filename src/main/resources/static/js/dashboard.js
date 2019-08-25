$( document ).ready(function() {
    var stompClient = null;

    var wallet = null;
    var rates = null;
    var cantorWallet = null;
    var transaction = {
        action: "",
        code: "",
        amount: 0
    };

    connect();
    getDataFromServer();

    function connect() {
        var socket = new SockJS('/websocket');
        stompClient = Stomp.over(socket);
        stompClient.debug = null;
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/ws/rates', function (message) {
                var response = JSON.parse(message.body);
                console.log(response);
                if(response.rates === null){
                    disableButtons();
                    $("#connectionError").show();
                }
                else{
                    rates = response.rates;
                    cantorWallet = response.cantorWallet;
                    refreshView();
                    enableButtons();
                    clearAlerts();

                    $("#pricesChangedError").show();
                    $("#acceptAction").prop('disabled', true);
                }

            });
        });
    }

    function getDataFromServer(){
        $.ajax({
            method: "GET",
            url: "/dashboard/transactionData",
            dataType: "json",
            contentType: "application/json",
            success: function (data) {
                wallet = data.wallet;
                rates = data.rates;
                cantorWallet = data.cantorWallet;

                if(rates === null){
                    disableButtons();
                    $("#connectionError").show();
                }
                else{
                    enableButtons();
                    refreshView();
                    $("#connectionError").hide();
                }
            }
        });
    }

    function refreshView(){
        refreshValues(rates, wallet, cantorWallet);
        calculateTotalValues();

        $("#funds").text('Available PLN: ' + wallet.funds);
        $("#cantorFunds").text('Cantor available PLN: ' + cantorWallet.funds);

        if(transaction.action === "BUY")
            updateBuyModalValues(transaction.code);

        if(transaction.action === "SELL")
            updateSellModalValues(transaction.code);
    }

    function refreshValues(rates, wallet, cantorWallet) {
        var date = rates.publicationDate;
        var dateParts = date.split('T');
        var time = dateParts[1].split('+')[0];
        $("#lastUpdate").text('Last update: ' + dateParts[0] + ' ' + time);

        $("#USDWalletAmount").text(wallet.usdAmount);
        $("#EURWalletAmount").text(wallet.eurAmount);
        $("#CHFWalletAmount").text(wallet.chfAmount);
        $("#RUBWalletAmount").text(wallet.rubAmount);
        $("#CZKWalletAmount").text(wallet.czkAmount);
        $("#GBPWalletAmount").text(wallet.gbpAmount);

        $("#USDCantorWalletAmount").text(cantorWallet.usdAmount);
        $("#EURCantorWalletAmount").text(cantorWallet.eurAmount);
        $("#CHFCantorWalletAmount").text(cantorWallet.chfAmount);
        $("#RUBCantorWalletAmount").text(cantorWallet.rubAmount);
        $("#CZKCantorWalletAmount").text(cantorWallet.czkAmount);
        $("#GBPCantorWalletAmount").text(cantorWallet.gbpAmount);

        $("#USDBuyPrice").text(rates.usd.purchaseValue);
        $("#EURBuyPrice").text(rates.eur.purchaseValue);
        $("#CHFBuyPrice").text(rates.chf.purchaseValue);
        $("#RUBBuyPrice").text(rates.rub.purchaseValue);
        $("#CZKBuyPrice").text(rates.czk.purchaseValue);
        $("#GBPBuyPrice").text(rates.gbp.purchaseValue);

        $("#USDBuyUnits").text(rates.usd.unit);
        $("#EURBuyUnits").text(rates.eur.unit);
        $("#CHFBuyUnits").text(rates.chf.unit);
        $("#RUBBuyUnits").text(rates.rub.unit);
        $("#CZKBuyUnits").text(rates.czk.unit);
        $("#GBPBuyUnits").text(rates.gbp.unit);

        $("#USDSellPrice").text(rates.usd.sellValue);
        $("#EURSellPrice").text(rates.eur.sellValue);
        $("#CHFSellPrice").text(rates.chf.sellValue);
        $("#RUBSellPrice").text(rates.rub.sellValue);
        $("#CZKSellPrice").text(rates.czk.sellValue);
        $("#GBPSellPrice").text(rates.gbp.sellValue);
    }

    function calculateTotalValues() {
        $("#USDWalletValue").text(Math.round(rates.usd.sellValue * wallet.usdAmount / rates.usd.unit * 10000) / 10000);
        $("#EURWalletValue").text(Math.round(rates.eur.sellValue * wallet.eurAmount / rates.eur.unit * 10000) / 10000);
        $("#CHFWalletValue").text(Math.round(rates.chf.sellValue * wallet.chfAmount / rates.chf.unit * 10000) / 10000);
        $("#RUBWalletValue").text(Math.round(rates.rub.sellValue * wallet.rubAmount / rates.rub.unit * 10000) / 10000);
        $("#CZKWalletValue").text(Math.round(rates.czk.sellValue * wallet.czkAmount / rates.czk.unit * 10000) / 10000);
        $("#GBPWalletValue").text(Math.round(rates.gbp.sellValue * wallet.gbpAmount / rates.gbp.unit * 10000) / 10000);

    }

    function disableButtons(){
        $("#acceptAction").prop('disabled', true);
        $(".sell-btn").prop('disabled', true);
        $(".buy-btn").prop('disabled', true);
    }

    function enableButtons(){
        $("#acceptAction").prop('disabled', false);
        $(".sell-btn").prop('disabled', false);
        $(".buy-btn").prop('disabled', false);
    }

    function clearAlerts() {
        $(".error").hide();
        $(".successAlert").hide();
    }

    $(".buy-btn").click(function () {
        clearAlerts();
        $("#acceptAction").prop('disabled', false);

        transaction.action = "BUY";
        transaction.code = $(this).val().toUpperCase();

        $("#actionType").text(transaction.action);
        $("#selectedCurrency").text(transaction.code);

        getDataFromServer();
    });

    $(".sell-btn").click(function () {
        clearAlerts();
        $("#acceptAction").prop('disabled', false);

        transaction.action = "SELL";
        transaction.code = $(this).val().toUpperCase();

        $("#actionType").text(transaction.action);
        $("#selectedCurrency").text(transaction.code);

        getDataFromServer();


    });

    function updateBuyModalValues(code) {
        var cost = 0;
        if(code === "USD") {
            transaction.amount = $("#USDBuyAmount").val() * rates.usd.unit;
            cost = transaction.amount * rates.usd.purchaseValue / rates.usd.unit;
        }
        if(code === "EUR") {
            transaction.amount = $("#EURBuyAmount").val() * rates.eur.unit;
            cost = transaction.amount * rates.eur.purchaseValue / rates.eur.unit;
        }
        if(code === "CHF") {
            transaction.amount = $("#CHFBuyAmount").val() * rates.chf.unit;
            cost = transaction.amount * rates.chf.purchaseValue / rates.chf.unit;
        }
        if(code === "RUB") {
            transaction.amount = $("#RUBBuyAmount").val() * rates.rub.unit;
            cost = transaction.amount * rates.rub.purchaseValue / rates.rub.unit;
        }
        if(code === "CZK") {
            transaction.amount = $("#CZKBuyAmount").val() * rates.czk.unit;
            cost = transaction.amount * rates.czk.purchaseValue / rates.czk.unit;
        }
        if(code === "GBP") {
            transaction.amount = $("#GBPBuyAmount").val() * rates.gbp.unit;
            cost = transaction.amount * rates.gbp.purchaseValue / rates.gbp.unit;
        }

        $("#currencyAmount").text(transaction.amount);
        $("#cost").text(cost);
    }
    function updateSellModalValues(code) {
        var cost = 0;
        if(code === "USD") {
            transaction.amount = $("#USDSellAmount").val();
            cost = transaction.amount * rates.usd.sellValue / rates.usd.unit;
        }
        if(code === "EUR") {
            transaction.amount = $("#EURSellAmount").val();
            cost = transaction.amount * rates.eur.sellValue / rates.eur.unit;
        }
        if(code === "CHF") {
            transaction.amount = $("#CHFSellAmount").val();
            cost = transaction.amount * rates.chf.sellValue / rates.chf.unit;
        }
        if(code === "RUB") {
            transaction.amount = $("#RUBSellAmount").val();
            cost = transaction.amount * rates.rub.sellValue / rates.rub.unit;
        }
        if(code === "CZK") {
            transaction.amount = $("#CZKSellAmount").val();
            cost = transaction.amount * rates.czk.sellValue / rates.czk.unit;
        }
        if(code === "GBP") {
            transaction.amount = $("#GBPSellAmount").val();
            cost = transaction.amount * rates.gbp.sellValue / rates.gbp.unit;
        }

        $("#currencyAmount").text(transaction.amount);
        $("#cost").text(cost);
    }

    $("#acceptAction").click(function () {
        $("#acceptAction").prop('disabled', true);

        $.ajax({
            method: "POST",
            url: "/dashboard/transaction",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(transaction),
            statusCode: {
                200: function(message) {
                    $("#successTransactionAlert").show();
                    getDataFromServer();
                },
                400: function (message) {
                    console.log(message);
                    $("#actionError").show();
                },
                404: function (message) {
                    console.log(message);
                    $("#connectionError").show();
                }
            }
        });


    });
});

