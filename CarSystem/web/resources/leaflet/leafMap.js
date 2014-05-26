// create a map in the "map" div, set the view to a given place and zoom
var leafMap;
var leafMarkers = {};

function initLeafMap()
{
    leafMap = L.map('leafMap').setView([51.505, 5.1], 13);
    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(leafMap);
}

function setLeafMarker(id, speed, lat, lon)
{
    var marker = leafMarkers[id];
    if(marker == null || marker == undefined)
    {
        marker = makeMarker(lat, lon);
        leafMarkers[id] = marker;
    }
    marker.setLatLng([lat, lon]);
    marker.setPopupContent("Cruisin at " + Math.round(speed * 100) / 100 + "m/s, or " +  Math.round(speed * 3.6 * 100) / 100 + "km/h." + "Ballin' yo.");
}

function makeMarker(lat, lon)
{
    var marker = L.marker([lat, lon], {icon: makeIcon()}).addTo(leafMap).bindPopup('Simulated car, look at it drive!');
    return marker;
}

function makeIcon()
{
    return L.icon({
        iconSize: [34, 34],
        iconAnchor: [17,17],
        popupAnchor: [0,0],
        iconUrl: 'javax.faces.resource/images/car.png'
    });
}

window.onload = initLeafMap;