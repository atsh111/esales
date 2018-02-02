var map;
var userMarkers =[];
var markers = [];
var route;
var ddlRoute;



function onGoToRouteClick() {
    if(ddlRoute)
        ddlRoute.setMap(null);
	var selRoute = $("#dropOperator").val();
	if(selRoute == 0)
		return;

    ddlRoute = new google.maps.Polyline({
        path: JSON.parse(routes[selRoute].data),
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 10
    });
    ddlRoute.setMap(map);

}
function addmarkers(s,e){

	var positions = $('#txtCoor').val().split(',');
	if(positions.length != 2)
	{
		console.log("Invalid position:"+positions);
		return;
	}
	/*var dmsCoords = ddToDms(positions[0], positions[1]);*/

	markers.push({lat:parseFloat(positions[0]),lng:parseFloat(positions[1])});

	var newmarker=new google.maps.LatLng(positions[0],positions[1]);
	var userMarker = new google.maps.Marker({
		position: newmarker,
		map: map,
		icon: im
	});
	userMarkers.push(userMarker);
}


function displayClickedPointCoor(lat,lng){
	$('#txtCoor').val(lat + ',' + lng);
}

function clearRoute(){
	if(route)
		route.setMap(null);
	if(ddlRoute)
		ddlRoute.setMap(null);
	clearAllMarkers();
}



// This example creates a 2-pixel-wide red polyline showing the path of
// the first trans-Pacific flight between Oakland, CA, and Brisbane,
// Australia which was made by Charles Kingsford Smith.

function initMap(){
	map = new google.maps.Map(document.getElementById('map'), {
		zoom: 15,
		/*center: {lat: 0, lng: -180},*/
		center:{lat:28.564487,lng:77.23835},
		mapTypeId: 'terrain'
	});

	var flightPlanCoordinates = [
		{lat: 37.772, lng: -122.214},
		{lat: 21.291, lng: -157.821},
		{lat: -18.142, lng: 178.431},
		{lat: -27.467, lng: 153.027}
	];
	var flightPlanTest=[
		{lat:28.564487,lng:77.23835},
		{lat:28.561499,lng:77.24944},
		{lat:28.563951,lng:77.259722},
		{lat:28.564487,lng:77.23835},
	];

	/*var flightPath = new google.maps.Polyline({
	path: flightPlanTest,
	geodesic: true,
	strokeColor: '#FF0000',
	strokeOpacity: 1.0,
	strokeWeight: 10
});*/

//flightPath.setMap(map);
}


var im = 'assets/images/bluecircle.png';
function locate(){
	initMap();
	initPosition();
	//var position=navigator.geolocation.getCurrentPosition(initialize,fail);
	//initialize(position);

}

function initialize(position) {
	var myLatLng = new google.maps.LatLng(position.lat, position.lng);
	var mapOptions = {
		zoom: 15,
		center: myLatLng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	}
	var userMarker = new google.maps.Marker({
		position: myLatLng,
		map: map,
		icon: im
	});
}

function fail(){
	alert('navigator.geolocation failed, may not be supported');
}

function createNewRoute(){
	markers.push(markers[0]);
	route = new google.maps.Polyline({
		path: markers,
		geodesic: true,
		strokeColor: '#FF0000',
		strokeOpacity: 1.0,
		strokeWeight: 10
	});
	route.setMap(map);
}


function initPosition() {

	var infoWindow = new google.maps.InfoWindow;

	// Try HTML5 geolocation.
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = {
				lat: position.coords.latitude,
				lng: position.coords.longitude
			};

			infoWindow.setPosition(pos);
			infoWindow.setContent('Location found.');
			infoWindow.open(map);
			map.setCenter(pos);
			initialize(pos);

			//Add listener
			google.maps.event.addListener(map, "click", function (event) {
				var latitude = event.latLng.lat();
				var longitude = event.latLng.lng();
				displayClickedPointCoor(latitude,longitude);
				console.log( latitude + ', ' + longitude );
			}); //end addListener
		}, function() {
			handleLocationError(true, infoWindow, map.getCenter());
		});
	} else {
		// Browser doesn't support Geolocation
		handleLocationError(false, infoWindow, map.getCenter());
	}
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow.setContent(browserHasGeolocation ?
		'Error: The Geolocation service failed.' :
		'Error: Your browser doesn\'t support geolocation.');
		infoWindow.open(map);
	}

	function clearAllMarkers(){
		for(var i=0;i<userMarkers.length;i++){
			userMarkers[i].setMap(null);
		}
		userMarkers=[];
		markers=[];


	}

	function exportRoute(){

		var routeName = $("#txtRouteName").val();
		var routeData = JSON.stringify(markers);
		var url = "/api/route/"+routeName;
		postTo(url,routeData,function(data){
			alert("Route saved successfully!");
			clearAllMarkers();
			markers.setMap(null);
		}, function(data){alert("Error while saving route")});
	}
