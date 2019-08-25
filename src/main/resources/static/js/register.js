$("#submit").click(function () {
    clearErrors();

    var firstName = $("#formFirstName").val();
    var lastName = $("#formLastName").val();
    var username = $("#formUsername").val();
    var email = $("#formEmail").val();
    var password = $("#formPass").val();

    if(!validateName(firstName)) return;
    if(!validateName(lastName)) return;
    if(!validateUsername(username)) return;
    if(!validateEmail(email)) return;
    if(!validatePassword(password)) return;
    if(!validateFundsAndCurrencies()) return;

    var funds = $("#funds").val();
    var usd = $("#usd").val();
    var eur = $("#eur").val();
    var chf = $("#chf").val();
    var rub = $("#rub").val();
    var czk = $("#czk").val();
    var gbp = $("#gbp").val();

    if(confirmPassword()){
        firstName = btoa(firstName);
        lastName = btoa(lastName);
        username = btoa(username);
        email = btoa(email);
        password = btoa(password);

        var registerForm = {firstName: firstName, lastName: lastName, username: username, password:password, email:email,
        funds: funds, usd: usd, eur: eur, chf: chf, rub: rub, czk: czk, gbp: gbp};
        var json = JSON.stringify(registerForm);

        $.ajax({
            method: "POST",
            url: "/register",
            dataType: "json",
            contentType: "application/json",
            data: json,
            statusCode: {
                409: function(message) {
                    showRegistrationError(message.responseText);
                },
                200: function() {
                    location.href = "/login";
                }
            }
        });
    }
    else {
        showPasswordDoNotMatchError()
    }

});
function clearErrors() {
    $(".error").hide();
}

function confirmPassword() {
    var pass = $("#formPass").val();
    var repass = $("#repass").val();

    return pass === repass;
}

function showRegistrationError(message) {
    if(message === "User already exists!")
        showUserExistsError();
    if(message === "Email already exists!")
        showEmailExistsError()
}

function showUserExistsError() {
    $("#usernameExistsError").show();
}

function showEmailExistsError() {
    $("#emailExistsError").show();
}

function validateName(name) {
    var regex = new RegExp("^[a-zA-Z -]{2,24}$");
    if(regex.test(name))
        return true;
    else{
        showInvalidNameFormatError();
        return false;
    }
}

function validateUsername(username){
    var regex = new RegExp("^[a-z0-9]{4,24}$");
    if(regex.test(username))
        return true;
    else{
        showInvalidUsernameFormatError();
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

function validateFundsAndCurrencies() {
    var fundsRegex = new RegExp("^\\d{1,10}.?\\d{0,4}$");
    var currencyReges = new RegExp("^\\d{1,10}$");

    if(!fundsRegex.test($("#funds").val())){
        $("#fundsFormatError").show();
        return false;
    }
    if(!currencyReges.test($("#usd").val())){
        $("#usdFormatError").show();
        return false;
    }
    if(!currencyReges.test($("#eur").val())){
        $("#eurFormatError").show();
        return false;
    }
    if(!currencyReges.test($("#chf").val())){
        $("#chfFormatError").show();
        return false;
    }
    if(!currencyReges.test($("#rub").val())){
        $("#rubFormatError").show();
        return false;
    }
    if(!currencyReges.test($("#czk").val())){
        $("#czkFormatError").show();
        return false;
    }
    if(!currencyReges.test($("#gbp").val())){
        $("#gbpFormatError").show();
        return false;
    }
    return true;
}

function showInvalidNameFormatError() {
    $("#nameFormatError").show();
}

function showInvalidUsernameFormatError() {
    $("#usernameFormatError").show();
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




