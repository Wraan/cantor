$( document ).ready(function() {
    var stompClient = null;

    var wallet = null;
    var rates = null;
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
                var latestRates = JSON.parse(message.body);
                refreshValues(latestRates);
                calculateTotalValues();

                clearAlerts();
                $("#pricesChangedError").show();
                $("#acceptAction").prop('disabled', true);
            });
        });
    }

    function refreshValues(rates) {
        $("#USDWalletAmount").text(wallet.usdAmount);
        $("#EURWalletAmount").text(wallet.eurAmount);
        $("#CHFWalletAmount").text(wallet.chfAmount);
        $("#RUBWalletAmount").text(wallet.rubAmount);
        $("#CZKWalletAmount").text(wallet.czkAmount);
        $("#GBPWalletAmount").text(wallet.gbpAmount);

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
        $("#USDWalletValue").text(Math.round($("#USDSellPrice").text() * $("#USDWalletAmount").text() * 10000) / 10000);
        $("#EURWalletValue").text(Math.round($("#EURSellPrice").text() * $("#EURWalletAmount").text() * 10000) / 10000);
        $("#CHFWalletValue").text(Math.round($("#CHFSellPrice").text() * $("#CHFWalletAmount").text() * 10000) / 10000);
        $("#RUBWalletValue").text(Math.round($("#RUBSellPrice").text() * $("#RUBWalletAmount").text() * 10000) / 10000);
        $("#CZKWalletValue").text(Math.round($("#CZKSellPrice").text() * $("#CZKWalletAmount").text() * 10000) / 10000);
        $("#GBPWalletValue").text(Math.round($("#GBPSellPrice").text() * $("#GBPWalletAmount").text() * 10000) / 10000);

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

    function getDataFromServer(){
        $.ajax({
            method: "GET",
            url: "/dashboard/transactionData",
            dataType: "json",
            contentType: "application/json",
            success: function (data) {
                wallet = data.wallet;
                rates = data.rates;

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
        refreshValues(rates);
        calculateTotalValues();

        $("#funds").text('Available PLN: ' + wallet.funds);

        if(transaction.action === "BUY")
            updateBuyModalValues(transaction.code);

        if(transaction.action === "SELL")
            updateSellModalValues(transaction.code);
    }

    function updateBuyModalValues(code) {
        var cost = 0;
        if(code === "USD") {
            transaction.amount = $("#USDBuyAmount").val() * rates.usd.unit;
            cost = transaction.amount * rates.usd.purchaseValue;
        }
        if(code === "EUR") {
            transaction.amount = $("#EURBuyAmount").val() * rates.eur.unit;
            cost = transaction.amount * rates.eur.purchaseValue;
        }
        if(code === "CHF") {
            transaction.amount = $("#CHFBuyAmount").val() * rates.chf.unit;
            cost = transaction.amount * rates.chf.purchaseValue;
        }
        if(code === "RUB") {
            transaction.amount = $("#RUBBuyAmount").val() * rates.rub.unit;
            cost = transaction.amount * rates.rub.purchaseValue;
        }
        if(code === "CZK") {
            transaction.amount = $("#CZKBuyAmount").val() * rates.czk.unit;
            cost = transaction.amount * rates.czk.purchaseValue;
        }
        if(code === "GBP") {
            transaction.amount = $("#GBPBuyAmount").val() * rates.gbp.unit;
            cost = transaction.amount * rates.gbp.purchaseValue;
        }

        $("#currencyAmount").text(transaction.amount);
        $("#cost").text(cost);
    }
    function updateSellModalValues(code) {
        var cost = 0;
        if(code === "USD") {
            transaction.amount = $("#USDSellAmount").val() * rates.usd.unit;
            cost = transaction.amount * rates.usd.sellValue;
        }
        if(code === "EUR") {
            transaction.amount = $("#EURSellAmount").val() * rates.eur.unit;
            cost = transaction.amount * rates.eur.sellValue;
        }
        if(code === "CHF") {
            transaction.amount = $("#CHFSellAmount").val() * rates.chf.unit;
            cost = transaction.amount * rates.chf.sellValue;
        }
        if(code === "RUB") {
            transaction.amount = $("#RUBSellAmount").val() * rates.rub.unit;
            cost = transaction.amount * rates.rub.sellValue;
        }
        if(code === "CZK") {
            transaction.amount = $("#CZKSellAmount").val() * rates.czk.unit;
            cost = transaction.amount * rates.czk.sellValue;
        }
        if(code === "GBP") {
            transaction.amount = $("#GBPSellAmount").val() * rates.gbp.unit;
            cost = transaction.amount * rates.gbp.sellValue;
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
                    console.log(message);
                },
                400: function (message) {
                    showEmailExistsError();
                    console.log(message);
                }
            }
        });


    });

    function clearAlerts() {
        $(".error").hide();
        $(".successAlert").hide();
    }


});

