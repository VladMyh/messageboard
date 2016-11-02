$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/messages",
        dataType: "json",
        method: "GET",
        headers: {"content-type": "application/json; charset=utf-8",
                "Access-Control-Allow-Origin": "*"}
    }).then(function(data) {
        $('.content').append("<p>" + data + "</p>>");
    });
});