$( document ).ready(function() {
    var stompClient = null;
    var latestRates = null;
    var funds = '${wallet.funds}';

    console.log(funds);

    connect();
    calculateTotalValues();

    function connect() {
        var socket = new SockJS('/websocket');
        stompClient = Stomp.over(socket);
        stompClient.debug = null;
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/ws/rates', function (message) {
                latestRates = JSON.parse(message.body);
                refreshValues(latestRates);
                calculateTotalValues();
                console.log(latestRates);
            });
        });
    }

    function refreshValues(rates) {
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
});