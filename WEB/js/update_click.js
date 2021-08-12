function updateClick(store){      
    $.ajax({
       type : "GET",
       url : "php/update_click_count.php",
       data: {store_name:store},
       dataType : "json",
       error : function(){ alert('통신실패!!');  },
       success : function(res){ 
          
          location.href = res.link;
         
        }
    });
}