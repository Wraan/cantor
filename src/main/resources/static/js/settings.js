$("#submitFunds").click(function () {
    clearAlerts();
    var funds = $("#funds").val();

    if(validateFunds(funds)){
        $.ajax({
            method: "POST",
            url: "/settings/funds?newValue=" + btoa(funds),
            statusCode: {
                200: function(message) {
                    showFundsChangeSuccess();
                    console.log(message.responseText);
                }
            }
        });
    }
});

$("#submitEmail").click(function () {
    clearAlerts();

    var email = $("#email").val();

    if(validateEmail(email)){
        $.ajax({
            method: "POST",
            url: "/settings/email?newValue=" + btoa(email),
            statusCode: {
                200: function(message) {
                    showEmailChangeSuccess();
                    console.log(message);
                },
                409: function (message) {
                    showEmailExistsError();
                    console.log(message);
                }
            }
        });
    }
});

$("#submitPassword").click(function () {
    clearAlerts();

    var password = $("#pass").val();

    if(validatePassword(password) && confirmPassword()){
        $.ajax({
            method: "POST",
            url: "/settings/password?newValue=" + btoa(password),
            statusCode: {
                200: function() {
                    showPasswordChangeSuccess();
                    console.log(message.responseText);
                }
            }
        });
    }
});

function clearAlerts() {
    $(".error").hide();
    $(".successAlert").hide();
}

function confirmPassword() {
    var pass = $("#pass").val();
    var repass = $("#repass").val();

    if(pass === repass)
        return true;
    else{
        showPasswordDoNotMatchError();
        return false;
    }
}

function showEmailExistsError() {
    $("#emailExistsError").show();
}

function validateFunds(funds){
    var regex = new RegExp("^\\d{1,10}(?:[.,]\\d{3})*(?:[.,]\\d{0,4})$");
    if(regex.test(funds) && funds >= 0)
        return true;
    else{
        showInvalidFundsFormatError();
        return false;
    }

}

function validateEmail(email){
    var regex = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    if(regex.test(email))
        return true;
    else{
        showInvalidEmailFormatError();
        return false;
    }
}

function validatePassword(password){
    var regex = new RegExp("^[a-zA-Z0-9]{6,24}$");
    if(regex.test(password))
        return true;
    else{
        showInvalidPasswordFormatError();
        return false;
    }
}

function showInvalidFundsFormatError() {
    $("#fundsFormatError").show();
}

function showInvalidEmailFormatError() {
    $("#emailFormatError").show();
}

function showInvalidPasswordFormatError() {
    $("#passwordFormatError").show();
}

function showPasswordDoNotMatchError() {
    $("#passwordNotMatchError").show();
}

function showFundsChangeSuccess() {
    $("#fundsChangeSuccess").show();
}

function showEmailChangeSuccess() {
    $("#emailChangeSuccess").show();
}

function showPasswordChangeSuccess() {
    $("#passwordChangeSuccess").show();
}