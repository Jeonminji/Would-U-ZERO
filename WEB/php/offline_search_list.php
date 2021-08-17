<?php
	$filter = $_GET['filter'];
	$page = $_GET['page'];
	$html = array();
	$line = mysqli_connect('127.0.0.1', 'root', '123', 'wuz');
	$sql = "select * from offline_info where store_type like '%$filter%'";
	$result = mysqli_query($link, $sql);
  $i = 0;
  while ($row = mysqli_fetch_array($result))
  {
    $html[$i] = $row;
    $i = $i + 1;
  }
  
  for($j = 0; ($j < 3) &&( $j < $i); $j = $j + 1)
  {
    $join_ary = array('\'',$html[$page * 3 + $j - 3]["store_name"],'\'');
    $ret = join("",$join_ary);

    echo('<div class="searched_list">
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
      <p>
      <a href='.$html[$page * 3 + $j - 3]['link'].'>&nbsp&nbsp 인스타그램으로 이동</a></p>
      </div>
    
      <a class="info_button" onclick="openModal('.$ret.')">더보기</a>
  </div>');
  }
?>