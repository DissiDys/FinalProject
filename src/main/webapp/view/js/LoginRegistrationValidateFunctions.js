function check() {
    document.getElementById("confirm_password").setAttribute("pattern", document.getElementById("password").value);
}

function clearErrorMsg() {
    document.getElementById('errorMsg').innerText = "";
}