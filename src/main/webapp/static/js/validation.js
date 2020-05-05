let MAX_LENGTH = 30;
let MIN_LENGTH = 1;

let WRONG_LOGIN = "Wrong login, it should be " + MIN_LENGTH + "-" + MAX_LENGTH + " letters";
let WRONG_NAME = "Wrong name, it should be " + MIN_LENGTH + "-" + MAX_LENGTH + " letters";
let WRONG_LAST_NAME = "Wrong last name, it should be " + MIN_LENGTH + "-" + MAX_LENGTH + " letters";
let WRONG_EMAIL = "Invalid email address";
let WRONG_PASSWORD = "Wrong password, it should be more than 8 symbols";
let WRONG_PASSWORD2 = "Wrong password confirmation, it should be similar to password";

let NAME_PATTERN = /[a-zA-Zа-яА]+/;
let PASSWORD_PATTERN = /^\w{8,}$/;
let EMAIL_PATTERN = /\S+@\S+\.\S+/;

const CHECK_FIELD = {
    emailtest: /\S+@\S+\.\S+/,
    password: /^\w{8,}$/,
    firstName: /[a-zA-Zа-яА]+/,
    lastName: /[a-zA-Zа-яА]+/
}
const ERROR_MESSAGE = {
    emailtest: "Invalid email address",
    password: "Wrong password, it should be more than 8 symbols",
    firstName: "Wrong name, it should be " + MIN_LENGTH + "-" + MAX_LENGTH + " letters",
    lastName: "Wrong last name, it should be " + MIN_LENGTH + "-" + MAX_LENGTH + " letters"
}

function validateEverything() {
    cleanErrors();
    let validationResult = true;
    let passwordResult;
    validationResult = validate("login", WRONG_LOGIN, NAME_PATTERN, MAX_LENGTH) && validationResult;
    validationResult = validate("firstName", WRONG_NAME, NAME_PATTERN, MAX_LENGTH) && validationResult;
    validationResult = validate("lastName", WRONG_LAST_NAME, NAME_PATTERN, MAX_LENGTH) && validationResult;
    validationResult = validate("email", WRONG_EMAIL, EMAIL_PATTERN) && validationResult;
    passwordResult = validate("password", WRONG_PASSWORD, PASSWORD_PATTERN);
    validationResult = passwordResult && validationResult;
    validationResult = validate("confirmPassword", WRONG_PASSWORD2, passwordResult) && validationResult;
    return validationResult;
}

function validate(fieldName, message, pattern, length) {
    let text = document.getElementById(fieldName).value;
    let isOk = length === undefined ? true : text.length <= length;
    if (isOk && pattern === false || !text.match(pattern)) {
        writeError(fieldName, message);
        return false;
    }
    return text;
}

function cleanErrors() {
    let errors = document.getElementsByClassName("errorMessage");
    if (errors != null && errors.length != 0) {
        for (let i = errors.length - 1; i >= 0; i--) {
            errors[i].remove();
        }
    }
}

function writeError(elementName, message) {
    let element = document.getElementById(elementName);
    let paragraphParent = element.parentElement;
    let paragraph = document.createElement("p");
    paragraph.className = "errorMessage";
    paragraph.innerHTML = message;
    paragraphParent.appendChild(paragraph);
}

function test() {
    cleanErrors();
    var elements = document.getElementsByClassName("form-control");
    for (var i = 0; i < elements.length; i++) {
        let checkField = CHECK_FIELD[elements[i].getAttribute("type")];
        if (checkField && !checkField.test(elements[i].value)) {
            var paragraphParent = elements[i].parentElement;
            let paragraph = document.createElement("p");
            paragraph.className = "errorMessage";
            paragraph.innerHTML = ERROR_MESSAGE[elements[i].getAttribute("type")];
            paragraphParent.appendChild(paragraph);
            validateTest = false;
        }
    }
    return validateTest;
}