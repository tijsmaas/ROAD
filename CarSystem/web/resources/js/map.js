var map;
var marker;

function initialize() {
    var mapOptions = {
        center: new google.maps.LatLng(0, -180),
        zoom: 3,
        mapTypeId: google.maps.MapTypeId.TERRAIN
    };

    map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

    marker = new google.maps.Marker({
        position: new google.maps.LatLng(0, -180),
        icon: {
            path: google.maps.SymbolPath.CIRCLE,
            scale: 5,
            fillColor: '#000000',
            fillOpacity: 1.0,
            strokeColor: '#000000',
            strokeOpacity: 1.0,
            strokeWeight: 2
        },
        draggable: false,
        map: map
    });

    var flightPlanCoordinates = [
        new google.maps.LatLng(37.772323, -122.214897),
        new google.maps.LatLng(21.291982, -157.821856),
        new google.maps.LatLng(-18.142599, 178.431),
        new google.maps.LatLng(-27.46758, 153.027892)
    ];
    var flightPath = new google.maps.Polyline({
        path: flightPlanCoordinates,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });

    flightPath.setMap(map);
}

function setMarker(lat, lon)
{
    var pos = new google.maps.LatLng(lat, lon);
    marker.setPosition(pos);
}

google.maps.event.addDomListener(window, 'load', initialize);