<?php
	$filter = $_GET['filter'];
	$page = $_GET['page'];
	$html = array();
	$link=mysqli_connect("localhost","admin","admin","test");
	$sql = "select * from offline_info where store_type like '%$filter%'";
	$result = mysqli_query($link, $sql);
  $i = 0;

  while ($row = mysqli_fetch_array($result))
  {
    $html[$i] = $row;
    $i = $i + 1;
  }

  for($i = 3; $i > 0; $i = $i - 1)
  {
    echo('<div class="searched_list">
    <a class="prev" onclick="plusSlides(-1,'.(3 - $i).')">
      <img src="img/point_prev.png">
    </a>
    <a class="next" onclick="plusSlides(1,'.(3 - $i).')">
      <img src="img/point_next.png">
    </a>
    
    <div class="slideshow-container">
      <div class="mySlides'.(4-$i).' fade">
        <img src="'.$html[$page * 3 - $i]["img1"].'" style="width:100%; height: 250px;">
      </div>
      <div class="mySlides'.(4-$i).' fade">
        <img src="'.$html[$page * 3 - $i]["img2"].'" style="width:100%; height: 250px;">
      </div>
      <div class="mySlides'.(4-$i).' fade">
        <img src="'.$html[$page * 3 - $i]["img3"].'" style="width:100%; height: 250px;">
      </div>
    </div>

    <div class="store_info">
      <p>'.$html[$page * 3 - $i]["store_name"].'</p>
      <p> 인스타 </p>
      <p> 운영시간 : '.$html[$page * 3 - $i]["opening_hours"].'</p>
      <a class="info_button" onclick="openModal()">더보기</a>
    </div>
  </div>');
  }
?>