function simulation()
{
    if ("WebSocket" in window)
    {
        var ws = new WebSocket("ws://localhost:8080/car/socket");
        ws.onopen = function()
        {
            $("#btnSim").prop( "disabled", true );
            ws.send("start");
        };
        ws.onmessage = function (evt)
        {
            var response = $.parseXML(evt.data);
            var timestep = response.children[0].children[1].attributes;
            var vehicle = response.children[0].children[1].children[0].attributes;
            $("#show").append("Vehicle with id " + vehicle.id.value +
                " with latitude  " + vehicle.y.value +
                ", longitude " + vehicle.x.value +
                " and speed " + vehicle.speed.value  +
                " @" + timestep.time.value + "<br />");
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