var currentRouteCollection =[];
function findRouteObjById(selIndex) {
    var result;
    $(routes).each(function (a, b) {
        if (b.id == selIndex)
        {
            result= b;
        }
    });
    return result;
}
function findRoutePathDetailsById(selIndex) {
    var obj=findRouteObjById(selIndex);
    return obj.data;
}
function fetchRoutes() {
    var selIndex=$("#dropOperator").val();
    if(selIndex ==0)
        return;
    var data;
    data=findRoutePathDetailsById(selIndex);
    data = JSON.parse(data);
    if(!data)
        return;
    currentRouteCollection.push(data);
    var newRoute = new google.maps.Polyline({
    path: data,
    geodesic: true,
        strokeColor: '#FF0000',
      	strokeOpacity: 1.0,
      		strokeWeight: 10
      	});
      	newRoute.setMap(map);

    map.setZoom(15);      // This will trigger a zoom_changed on the map
    map.setCenter(new google.maps.LatLng(data[0].lat,data[0].lng));
    map.setMapTypeId(google.maps.MapTypeId.ROADMAP);
}


function routeChangeEventHandler(){

    var selRoute = $("#dropOperator").val();
    if(selRoute == 0)
        return;
    var routeName=$("#dropOperator option:selected").text();
    fetchRouteDetails(routeName);
}

function fetchRouteDetails(routeName) {

    var url = "/api/routeuser/getbyroutename/"+routeName;
    get(url,null, function(data){
        $('#lstSrc,#lstTgt')
            .find('option')
            .remove()
            .end()

        for (i = 0; i < data.users.length; i++) {
            var innerText = '<option class="list-group-item" value="'+data.users[i].id+'">' + data.users[i].userName + '</option>'
            $('#lstTgt').append(innerText);
        }

        for (i = 0; i < data.unassignedUsers.length; i++) {
            var innerText = '<option class="list-group-item" value="'+data.unassignedUsers[i].id+'">' + data.unassignedUsers[i].userName + '</option>'
            $('#lstSrc').append(innerText);
        }



        $('select').DualListBox();
        //$('lstTgt').DualListBox();
    })

}

$(function () { function moveItems(origin, dest) {
    $(origin).find(':selected').appendTo(dest);
}

    function moveAllItems(origin, dest) {
        $(origin).children().appendTo(dest);
    }

    $('#left').click(function () {
        moveItems('#lstTgt', '#lstSrc');
    });

    $('#right').on('click', function () {
        moveItems('#lstSrc', '#lstTgt');
    });

    $('#leftall').on('click', function () {
        moveAllItems('#lstTgt', '#lstSrc');
    });

    $('#rightall').on('click', function () {
        moveAllItems('#lstSrc', '#lstTgt');
    });
});







function saveHandler() {
    var selIndex=$("#dropOperator").val();
    if(selIndex ==0)
        return;
    var selRoute = findRouteObjById(selIndex);
    var routeDetails =new Object();
    routeDetails.routeId = selRoute.id;
    var assignedUsers = [];
    $("#lstTgt option").each(function () {
        assignedUsers.push(this.value);
    });
    routeDetails.users = assignedUsers;
    var postData = JSON.stringify(routeDetails);
    var url ="/api/routeuser/";
    postTo(url,postData,function () {
        bootbox.alert("Route User Mapping Saved",function(){
        $('#myModal').modal('toggle');postSave();});
    },function (error) {
        bootbox.alert("error while saving user mapping.",function(){
            $('#myModal').modal('toggle');postSave();});
    });
    }
    
    function postSave() {
        window.location.reload(true);
    }

