function createLocation(){
    var loc=new Object();
    loc.name=$("#txtName").val();
    loc.gstno=$("#txtGSTNo").val();
    loc.address=$("#txtAddress").val();
    var locationJson = JSON.stringify(loc);
    var url = "/api/locations/";
    postTo(url,locationJson,function(data){
        bootbox.alert("Location saved successfully!",function () {
            postLocationSave();
            location.reload();
        })

    }, function(data){
        bootbox.alert("error while saving location!",function () {
            postLocationSave();
            location.reload();
        })
    });
}

function postLocationSave() {
    
}