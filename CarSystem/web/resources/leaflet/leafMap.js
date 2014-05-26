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

function setLeafMarker(id, lat, lon)
{
    var marker = leafMarkers[id];
    if(marker == null || marker == undefined)
    {
        leafMarkers[id] = makeMarker(lat, lon);
    }
    marker.setLatLng([lat, lon]);
}

function makeMarker(lat, lon)
{
    var marker = L.marker([lat, lon]).addTo(leafMap).bindPopup('Simulated car, look at it drive!');
    return marker;
}

window.onload = initLeafMap;