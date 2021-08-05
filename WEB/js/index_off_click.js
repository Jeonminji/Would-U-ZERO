var btn = document.querySelectorAll('.offline_btn');
var offBtn_outline = document.querySelector('.off_box3');
var btn_count = btn.length - 1;
var off_timer = undefined;
btn[0].click();
var off_clickIdx = 1;

function off_autoClick(){
  if(off_timer == undefined){
      off_timer = setInterval(function(){
          btn[off_clickIdx].click();

          if(off_clickIdx == btn_count){
            off_clickIdx = 0;
          }
          else{
            off_clickIdx = off_clickIdx + 1;
          }
      }, 3000);
  }
}

off_autoClick();

function stopAutoClick(){
  clearInterval(off_timer);
  off_timer = undefined;
}

offBtn_outline.addEventListener('mouseenter', function(){
  stopAutoClick();
});

offBtn_outline.addEventListener('mouseleave', function(){
  off_autoClick();
});


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




