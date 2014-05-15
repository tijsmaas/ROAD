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
            var response = $.parseJSON(evt.data);
            var vehicle = response.timeStep.edges[0].lanes[0].vehicles[0];
            $("#show").append("Vehicle with id " + vehicle.id +
                              " with position  " + vehicle.pos +
                              " and speed " + vehicle.speed  +
                              " @" + response.timeStep.time + "<br />");
            $("#message").html(response.message);
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