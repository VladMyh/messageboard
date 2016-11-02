$(document).ready(function() {

    function appendMessage(elem, toAppend) {
        elem.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"><b>" +
                            toAppend.author + "</b> : " + toAppend.message + "</div></div>");
    }


    setInterval(function() {
        document.getElementById("messages").innerHTML = "";
        $.ajax({
            url: "http://localhost:8080/messages",
            dataType: "json",
            method: "GET",
            headers: {"content-type": "application/json; charset=utf-8",
                "Access-Control-Allow-Origin": "*"}
        }).then(function(data) {
            data.forEach(function (d) {
                appendMessage($('.content'), d);
            })
        });
    }, 3000);

});