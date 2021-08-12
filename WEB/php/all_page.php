<?php
    header("Content-Type: application/json");
    $link=mysqli_connect("localhost","admin","admin","WZ");

    if(isset($_GET["page"])){
        $page = $_GET["page"]; //하단에서 다른 페이지 클릭하면 해당 페이지 값 가져와서 보여줌
    } else {
        $page = 1; //게시판 처음 들어가면 1페이지로 시작
    }
    
    if ($page <= 1){ } 
	else {
		if(isset($unum)){
			echo "<li class='page-item'><a class='page-link' href='online.php?page=1' aria-label='Previous'>처음</a></li>";
	    } else {
		echo "<li class='page-item'><a class='page-link' href='online.php?page=1' aria-label='Previous'>처음</a></li>";
	    }
    }
									
	if ($page <= 1){ } 
	else {
		$pre = $page - 1;

		if(isset($unum)){
			echo "<li class='page-item'><a class='page-link' href='online.php?page=$pre'>◀&nbsp이전</a></li>";
		} else {
			echo "<li class='page-item'><a class='page-link' href='online.php?page=$pre'>◀&nbsp이전</a></li>";
		}
	}
									
	for($i = $block_start; $i <= $block_end; $i++){
		if($page == $i){
			echo "<li class='page-item'><a class='page-link' disabled><b style='color: #df7366;'>$i</b></a></li>";
		} else {
		    if(isset($unum)){
				echo "<li class='page-item'><a class='page-link' href='online.php?page=$i'>$i</a></li>";
		    } else {
				echo "<li class='page-item'><a class='page-link' href='online.php?page=$i'>$i</a></li>";
		    }
		}
	}
									
	if($page >= $total_page){ } 
	else {
		$next = $page + 1;
		if(isset($unum)){
			echo "<li class='page-item'><a class='page-link' href='online.php?page=$next'>다음&nbsp▶</a></li>";
		} else {
			echo "<li class='page-item'><a class='page-link' href='online.php?page=$next'>다음&nbsp▶</a></li>";
		}
	}
								
	if($page >= $total_page){ } 
	else {
		if(isset($unum)){
			echo "<li class='page-item'><a class='page-link' href='online.php?page=$total_page'>마지막</a>";
		} else {
			echo "<li class='page-item'><a class='page-link' href='online.php?page=$total_page'>마지막</a>";
		}
	}
?>
