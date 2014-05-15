function simulation()
{
    if ("WebSocket" in window)
    {
        var ws = new WebSocket("ws://localhost:8080/car/socket");
        ws.onopen = function()
        {
            ws.send("start");
        };
        ws.onmessage = function (evt)
        {
            var received_msg = evt.data;
            $("#show").append(received_msg + "<br />");
        };
        ws.onclose = function()
        {
            // websocket is closed.
            alert("Connection is closed...");
        };
    }
    else
    {
        alert("WebSocket NOT supported by your Browser!");
    }
}