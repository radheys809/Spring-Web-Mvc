$(document).ready(function () {

    $("#form-search").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });

});

function fire_ajax_submit() {
    var search = {}
    search["userName"] = $("#username").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:9990/rest/login",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = JSON.stringify(data, null, 4);

            $("#name").html(data.name);
            $("#mail").html(data.email);
            $("#city").html(data.city);
            $("#country").html(data.country);
            $("#profession").html(data.profession);
            $("#phone").html(data.msisdn);
            $('#feedback').html("Sucesss" + json);
            console.log("SUCCESS : ", data);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html("Failure :" + json);

            console.log("ERROR : ", e);
        }
    });

}

function fire_login_submit() {
    var login = {}
    login["userName"] = $("#loginName").val();
    login["passWord"] = $("#password").val();
    $("#btn-login").prop("disabled", true);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:9990/rest/login",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#userWelcome').html(json);
            console.log("SUCCESS : ", data);
            $("#btn-login").prop("disabled", false);
        },
        error: function (e) {
            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
        }
    });
}

function hello() {
    var login = {}
    login["userName"] = document.getElementById("loginName");
    login["passWord"] = document.getElementById("password");
    document.getElementById("demo").innerHTML = "Hello JavaScript" + login["userName"];


}

function loadDoc() {
    var xhttp = new XMLHttpRequest();
    var url = "http://localhost:9990/rest/login";
    alert("hello");
    xhttp.open("GET", url, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    var data = "username:ysys";
    xhttp.send(JSON.stingify(data));
    document.getElementById("demo").innerHTML = xhttp.responseText;
}