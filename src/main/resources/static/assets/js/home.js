var map;
var userMarkers =[];
var markers = [];
var route;
var ddlRoute;

var lRoutes=[];
var lInfoWindow;
function onGoToRouteClick() {
    if(ddlRoute)
        ddlRoute.setMap(null);
	var selRoute = $("#dropOperator").prop('selectedIndex');
	if(selRoute == 0)
		return;
	selRoute=selRoute-1;
    var routeData= JSON.parse(routes[selRoute].data)
    ddlRoute = new google.maps.Polygon({
        path: routeData,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 10
    });
    ddlRoute.setMap(map);
    map.setCenter(routeData[0]);
    google.maps.event.addListener(ddlRoute, 'click', function (event) {
        var contentString = routes[selRoute].name;
        var infoWindow = new google.maps.InfoWindow;
        // Replace the info window's content and position.
        infoWindow.setContent(contentString);
        infoWindow.setPosition(routeData[0]);
        infoWindow.open(map);
    });

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
/*	if(ddlRoute)
		ddlRoute.setMap(null);*/
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
		mapTypeId: google.maps.MapTypeId.ROADMAP,
        disableDoubleClickZoom: true
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
    var pos = {
        lat: 28.525717489483185,
        lng: 77.2586774834781
    };

			infoWindow.setPosition(pos);
			infoWindow.setContent('Location found.');
			infoWindow.open(map);
			map.setCenter(pos);
			initialize(pos);
			//Add listener
			google.maps.event.addListener(map, "rightclick", function (event) {
				var latitude = event.latLng.lat();
				var longitude = event.latLng.lng();
				displayClickedPointCoor(latitude,longitude);
                addmarkers();
				console.log( latitude + ', ' + longitude );
			}); //end addListener

    google.maps.event.addListener(map, "click", function (event) {
        var latitude = event.latLng.lat();
        var longitude = event.latLng.lng();
        displayClickedPointCoor(latitude,longitude);
        console.log( latitude + ', ' + longitude );
    });
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

function postRouteSave() {
		$('#btnCloseModal').click();
    clearAllMarkers();
    route.setMap(null);
    $("#txtCoor").val('');
    $("#txtRouteName").val('');
    window.location.reload(true);
}
function exportRoute(){

		var routeName = $("#txtRouteName").val();
		var routeData = JSON.stringify(markers);
		var locid = $("#ddlLoc").val();
		if(locid < 1)
			return;
		var url = "/api/route/"+routeName+"/"+locid;
		postTo(url,routeData,function(data){
			bootbox.alert("Route saved successfully!",function () {
                postRouteSave();
            })

		}, function(data){
            bootbox.alert("error while saving route!",function () {
                postRouteSave();
            })
		});
	}

function  clickRouteUpload() {
    $('#exampleModal').on('show.bs.modal', function (event) {
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('Save Route');
        modal.find('.modal-body input').val()
    })
}

function clickHideAll() {
    for(var j=0;j<lRoutes.length;j++){
			if(lRoutes[j])
				lRoutes[j].setMap(null);
			if(lInfoWindow)
            	lInfoWindow.setMap(null);
        }
		lRoutes =[];
}

function clickShowAll() {
	if(ddlRoute)
		ddlRoute.setMap(null);
	for(var j=0;j<lRoutes.length;j++){
		lRoutes[j].setMap(null);
	}

	for(var i=0;i<routes.length;i++) {
        var routeData = JSON.parse(routes[i].data)
        var lRoute = new google.maps.Polygon({
            path: routeData,
            geodesic: true,
            strokeColor: '#FF0000',
            strokeOpacity: 1.0,
            strokeWeight: 10
        });
        lRoute.name = routes[i].name;
        lRoute.pos = routeData[0];
        lRoute.setMap(map);
        lRoutes.push(lRoute);
        var rName = routes[i].name;
        var rPos = routeData[0];
        google.maps.event.addListener(lRoutes[i], 'click', function (event) {
            lInfoWindow=new google.maps.InfoWindow();
            lInfoWindow.setContent(this.name);
            lInfoWindow.setPosition(this.pos);
            lInfoWindow.open(map);
        });
    }

}

function makeCallback(rName,rPos) {
    return function() {
        setInfoWindow(rName,rPos);
    };
}


function setInfoWindow(rname,rPos) {
    var infoWindow = new google.maps.InfoWindow;
    // Replace the info window's content and position.
    infoWindow.setContent(rname);
    infoWindow.setPosition(rPos);
    infoWindow.open(map);
}



