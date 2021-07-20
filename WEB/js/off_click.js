var btn = document.querySelectorAll('.offline_btn');

fisrtClick();
function fisrtClick(){
  btn[0].click();
}

function info(store){      
    $.ajax({
       type : "POST",
       url : "php/index_off_info.php",
       data: {store_name:store},
       dataType : "json",
       error : function(){ alert('통신실패!!');  },
       success : function(res){ 
          var name = document.querySelector('.off_store_name').querySelector('span');
          var address = document.querySelector('.off_store_loc').querySelector('span');
          var type = document.querySelector('.off_store_type').querySelector('span');
          var lat = res.lat;
          var lng = res.lng;
          maps(lat,lng);
        
          name.innerText = res.store_name;
          address.innerText = res.address;
          type.innerText = res.store_type;
        }
    });
}




