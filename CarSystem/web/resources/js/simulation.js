var ws;

function simulation()
{
    $("#btnSim").prop( "disabled", true );
    ws.send("start");
}

function initSimulation()
{
    if ("WebSocket" in window)
    {
        ws = new WebSocket("ws://localhost:8080/car/socket");
        ws.onmessage = function (evt)
        {
            var response = $.parseXML(evt.data);
            var vehicles = response.children[0].children[1].children;
            for(var i = 0; i < vehicles.length; i++)
            {
                var vehicle = vehicles[i].attributes;
                setLeafMarker(vehicle.id.value, vehicle.y.value, vehicle.x.value);
            }
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

window.onload = initSimulation;