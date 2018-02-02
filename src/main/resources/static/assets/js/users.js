function createUser(){
    var user=new Object();
    user.fullName=$("#txtFName").val();
    user.email=$("#txtUserEmail").val();
    user.userId=$("#txtUserEmail").val();
    user.username=$("#txtUserName").val();
    user.password=$("#txtPassword").val();
    user.locationId = $("#ddlLoc").val();
    user.isAdmin=false;
    var userJson = JSON.stringify(user);
    var url = "/api/users/";
    postTo(url,userJson,function(data){
        bootbox.alert("User saved successfully!",function () {
            postUserSave();
            location.reload();
        })

    }, function(data){
        bootbox.alert("error while saving route!",function () {
            postUserSave();
            location.reload();
        })
    });
}

function postUserSave() {
    $('#btnCloseModal').click();

}

function  clickUserModal() {
    $('#exampleModal').on('show.bs.modal', function (event) {
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('Save User');
        modal.find('.modal-body input').val()
    })
}

