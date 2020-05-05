let MAX_LENGTH = 30;
let MIN_LENGTH = 1;

let WRONG_NAME = "Wrong name, it should be " + MIN_LENGTH + "-" + MAX_LENGTH + " letters";
let WRONG_LAST_NAME = "Wrong last name, it should be " + MIN_LENGTH + "-" + MAX_LENGTH + " letters";
let WRONG_EMAIL = "Invalid email address";
let WRONG_PASSWORD = "Wrong password, it should be more than 8 symbols";
let WRONG_PASSWORD2 = "Wrong password confirmation, it should be similar to password";

let NAME_PATTERN = /[a-zA-Zа-яА]+/;
let PASSWORD_PATTERN = /^\w{8,}$/;
let EMAIL_PATTERN = /\S+@\S+\.\S+/;

$(document).ready(function () {

    function validate(fieldName, message, pattern, length) {
        let text = $("#" + fieldName).val();
        let isOk = length === undefined ? true : text.length <= length;
        if (isOk && (pattern === false || !text.match(pattern))) {
            writeError(fieldName, message);
            return false;
        }
        return text;
    }

    function writeError(elementName, message) {
        jQuery("<p>", {
            "class": "errorMessage", text: message
        }, "</p>").appendTo($("#" + elementName).parent());
    }

    function validateEverything() {
        $(".errorMessage").remove();
        let validationResult = true;
        let passwordResult;

        validationResult = validate("firstName", WRONG_NAME, NAME_PATTERN, MAX_LENGTH) && validationResult;
        validationResult = validate("lastName", WRONG_LAST_NAME, NAME_PATTERN, MAX_LENGTH) && validationResult;
        validationResult = validate("email", WRONG_EMAIL, EMAIL_PATTERN) && validationResult;
        passwordResult = validate("password", WRONG_PASSWORD, PASSWORD_PATTERN);
        validationResult = passwordResult && validationResult;
        validationResult = validate("confirmPassword", WRONG_PASSWORD2, passwordResult) && validationResult;
        return validationResult;
    }

    $("#registrationForm").removeAttr("onsubmit").submit(validateEverything);
});