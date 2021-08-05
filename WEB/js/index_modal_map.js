function modal_maps(lat,lng){
    var mapContainer = document.querySelector(".modal_map"); 
    var mapOption = { 
        center: new kakao.maps.LatLng(lat, lng), 
        level: 3         };

    var map = new kakao.maps.Map(mapContainer, mapOption); 
        
    var markerPosition  = new kakao.maps.LatLng(lat, lng); 
    
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });
    
    marker.setMap(map); 
    
    
}