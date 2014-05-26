// create a map in the "map" div, set the view to a given place and zoom
var lmap;
var lmarker;

function initlMap()
{
    lmap = L.map('lmap').setView([51.505, 5.1], 13);
    lmarker = L.marker([51.5, 5.1]).addTo(lmap).bindPopup('Simulated car, look at it drive!').openPopup();
}

function setlMarker(lat, lon)
{
    lmarker.setLatLng([lat, lon]);
    //lmap.panTo([50, 30]);
}

window.onload = initlMap;