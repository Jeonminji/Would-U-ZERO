// image slide
var slideIndex = [1,1,1];
var slideId = ["mySlides1", "mySlides2", "mySlides3"];

function plusSlides(n, no) {
  showSlides(slideIndex[no] += n, no);
}

function showSlides(n, no) {
  var i;
  var x = document.getElementsByClassName(slideId[no]);
  if (n > x.length) {slideIndex[no] = 1}
  if (n < 1) {slideIndex[no] = x.length}
  for (i = 0; i < x.length; i++) {
	 x[i].style.display = "none";  
  }
  x[slideIndex[no]-1].style.display = "block";  
}

// map function
function map_to_modal(name)
{
  return function() {
    openModal(name);
  };
}

// offline first page map
function overall_map(ary) {
  var mapContainer = document.getElementById('map'),
      mapOption = { 
          center: new kakao.maps.LatLng(37.5456, 126.9798),
          level: 8
        };
  var map = new kakao.maps.Map(mapContainer, mapOption);

  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

  for (var i = 0; i < ary.length; i++)
  {
      var imageSize = new kakao.maps.Size(24,35);
      var markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize);

      var marker = new kakao.maps.Marker({
          map: map,
          position: new kakao.maps.LatLng(ary[i]['lat'], ary[i]['lng']),
          title: ary[i]['store_name'],
          image: markerImg
        });

      kakao.maps.event.addListener(marker, 'click', map_to_modal(ary[i]['store_name']));
    }
}

// all_searched_map
function all_searched_map(count, ary) {
  var mapContainer = document.getElementById('map'),
      mapOption = { 
          center: new kakao.maps.LatLng(37.5456, 126.9798),
          level: 9
      };
  var map = new kakao.maps.Map(mapContainer, mapOption);

  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

  for (var i = 3; i > 0; i--)
  {
      var imageSize = new kakao.maps.Size(24,35);

      var markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize);

      var marker = new kakao.maps.Marker({
          map: map,
          position: new kakao.maps.LatLng(ary[count * 3 - i]['lat'], ary[count * 3 - i]['lng']),
          title: ary[count * 3 - i]['store_name'],
          image: markerImg
      });

    kakao.maps.event.addListener(marker, 'click', map_to_modal(ary[count * 3 - i]['store_name']));
  }
}

// modal map
function modal_maps(res){
  var lat = res['lat'];
  var lng = res['lng'];
  var mapContainer = document.getElementById("map2"); 
  var mapOption = { 
      center: new kakao.maps.LatLng(lat, lng), 
      level: 3
    };

    var map = new kakao.maps.Map(mapContainer, mapOption); 
        
    var markerPosition  = new kakao.maps.LatLng(lat, lng); 
    
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });
    marker.setMap(map);
}

function modal_info(res)
{
  const element = document.getElementById("modal_in_info");
  element.innerHTML = ('<div style="height:50px; margin-top: 30%">' +
  '<div style="width: 90%; float:left">' + 
    '<p style="text-align:center; font-size: larger;">'+ res['store_name']+'</p> </div>' +
  '<div style="float:center">' + 
    '<a class="close_cursor" onclick="closeModal()">&times;</a></div></div>' + 
  '<div class="secific_info" style="margin-bottom: 2%; margin-left: 30px;">' + 
  '<p> 주소 : '+ res['address'] +'</p>' +
  '<a href="'+ res['link'] +'" sytle="cursor: pointer"> 인스타그램 :'+ res['link']+'</a>' +
  '<p> 전화번호 : '+ res['store_num'] +'</p>' +
    '<p> 운영시간 : '+res["opening_hours"]+'</p>' +
  '<p> 추가정보 : '+res["other_info"]+'</p></div>');
}

function modal_inner_info(name)
{
  $.ajax({
    type : "POST",
    url : "php/offline_search_info.php",
    data: {
      store_name:name
    },
    dataType : "json",
    error : function(){ alert('통신실패!!');  },
    success : function(res){ 
        modal_info(res);
        modal_maps(res);
     }
  });
}

const openModal = (name) => {
  document.getElementById("myModal").style.display = "block";
  modal_inner_info(name);
 }

 function closeModal() {
  document.getElementById("myModal").style.display = "none";
}
