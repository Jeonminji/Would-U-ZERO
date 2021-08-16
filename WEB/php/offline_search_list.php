<?php
	$filter = $_GET['filter'];
	$page = $_GET['page'];
	$html = array();
	$link = mysqli_connect("localhost", "admin", "admin", "WZ");
	$sql = "select * from offline_info where store_type like '%$filter%'";
	$result = mysqli_query($link, $sql);
  $i = 0;
  while ($row = mysqli_fetch_array($result))
  {
    $html[$i] = $row;
    $i = $i + 1;
  }
  
  $margin = 0;
  if ($i % 3 == 0)
    $margin = 4;
  else
    $margin = 37.5;

  for($j = 0; ($j < 3) &&( $j < $i); $j = $j + 1)
  {
    $join_ary = array('\'',$html[$page * 3 + $j - 3]["store_name"],'\'');
    $ret = join("",$join_ary);

    echo('<div class="searched_list" style="margin-left:'.$margin.'%">
    <a class="prev" onclick="plusSlides(-1,'.$j.')">
      <img src="img/point_prev.png">
    </a>
    <a class="next" onclick="plusSlides(1,'.$j.')">
      <img src="img/point_next.png">
    </a>

    <div class="slideshow-container">
      <div class="mySlides'.($j + 1).' fade">
        <img src="'.$html[$page * 3 + $j - 3]["img1"].'" style="width:100%; height: 250px;">
      </div>
      <div class="mySlides'.($j + 1).' fade">
        <img src="'.$html[$page * 3 + $j - 3]["img2"].'" style="width:100%; height: 250px;">
      </div>
      <div class="mySlides'.($j + 1).' fade">
        <img src="'.$html[$page * 3 + $j - 3]["img3"].'" style="width:100%; height: 250px;">
      </div>
    </div>

    <div class="store_info">
      <p style="font-size: larger; text-align: center;">'.$html[$page * 3 + $j - 3]["store_name"].'</p>
      <p> &nbsp운영시간 </br></p>
      <p>&nbsp&nbsp'.$html[$page * 3 + $j - 3]["opening_hours"].'</p>
      <p> &nbsp인스타그램 </br></p>
      <a  style="font-size:15px;" href='.$html[$page * 3 + $j - 3]['link'].'>&nbsp&nbsp'.$html[$page * 3 + $j - 3]['link'].'</a>
      </div>
    
      <a class="info_button" onclick="openModal('.$ret.')">더보기</a>
  </div>');
  }
?>