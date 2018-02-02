
function postTo(url,data,onSuccess,onFailure){
$.ajax({
   type: "POST",
   url: url,
   data: data,
   success: function(data){
      onSuccess(data);
      },
   failure: function(data){
      onFailure(data);
      },
   contentType:"application/json; charset=utf-8",
   dataType: "json"
 });
}

function get(url,data,onSuccess,onFailure){
$.ajax({
   type: "GET",
   url: url,
   success: function(data){onSuccess(data);},
   failure: function(data){onFailure(data);},
   contentType:"application/json; charset=utf-8",
   dataType: "json"
 });
}
