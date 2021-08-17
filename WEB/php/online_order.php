<?php
	ini_set('display_errors', '0'); //에러 메시지 출력 x
    $db_host = "localhost"; 
    $db_user = "admin";
    $db_passwd = "admin";
    $db_name = "WZ";
    header("Content-Type:text/html;charset=utf-8");

    $link = mysqli_connect($db_host, $db_user, $db_passwd, $db_name); //DB 접속

	// SQL 쿼리문 간단하게 쓰기 위한 함수 mq 선언
    function mq($sql){
        global $link;
        return $link->query($sql);
    }

    // 현재 페이지 번호
    if(isset($_GET["page"])){
        $page = $_GET["page"];
    } else {
        $page = 1;
    }

    $order = $_GET['order'];
	$range = $_GET['range'];
?>

<!DOCTYPE html>
<html>
  <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
      <meta name = "viewport" content = "width=device-width, initial-scale = 1.0">
      <title>Would U ZERO?</title>
      <link rel="preconnect" href="https://fonts.gstatic.com">
      <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@500&display=swap" rel="stylesheet">
      <link href="http://fonts.googleapis.com/earlyaccess/jejugothic.css" rel="stylesheet" >
	  <script src="https://kit.fontawesome.com/5fd881ff65.js" crossorigin="anonymous"></script>
      <link rel="stylesheet" href="../css/header.css?after">
      <link rel="stylesheet" href="../css/category.css?after">
	  <link rel="stylesheet" href="../css/online.css?a">
	  <script src = "../js/update_click.js"></script>
  </head>
  <body>
	<!-- header -->
    <nav class="navbar">
        <div class="navbar_logo">
			<div><img class ="logo_img" src="../img/planet.png"></div>
      		<div><a href="">WOULD U ZERO?</a></div>
        </div>
        <ul class="navbar_category">
            <li><a href="../index.html">Home</a></li>
            <li><a href="../about.html">About</a></li>
            <li><a href="../online.php">Online</a></li>
            <li><a href="../offline.html">Offline</a></li>
        </ul>
        <div class="navbar_toogleBtn">
            <i class="fas fa-bars"></i>
        </div>
    </nav>
  	<!-- header end -->

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	<script>
			$(document).ready(function(){
				
				//모든 서브 메뉴 감추기
				$(".sub").css({display:"none"});  

				$(".gnb > li > a").click(function() {
					$(".sub").css({display:"none"}); //다른 서브메뉴 감추기
					$(this).next("ul").slideDown(300);
				})
			});
		</script>

<div id="wrap">
		<header class="category">
			<nav>
				<ul class="gnb">
					<li><a href="#주방">주방</a>
						<ul class="sub">
							<li><a href="online_category.php?main_category=주방&sub_category=세제">세제</a></li>
							<li><a href="online_category.php?main_category=주방&sub_category=수세미">수세미</a></li>
							<li><a href="online_category.php?main_category=주방&sub_category=행주">행주</a></li>
							<li><a href="online_category.php?main_category=주방&sub_category=솔">솔</a></li>
							<li><a href="online_category.php?main_category=주방&sub_category=기타">기타</a></li>
						</ul>
					</li>
					<li><a href="#욕실">욕실</a>
						<ul class="sub">
							<li><a href="online_category.php?main_category=욕실&sub_category=헤어">헤어</a></li>
							<li><a href="online_category.php?main_category=욕실&sub_category=바디">바디</a></li>
							<li><a href="online_category.php?main_category=욕실&sub_category=구강">구강</a></li>
							<li><a href="online_category.php?main_category=욕실&sub_category=기타">기타</a></li>
						</ul>
					</li>
					<li><a href="#의류">의류</a>
						<ul class="sub">
							<li><a href="online_category.php?main_category=의류&sub_category=상의">상의</a></li>
							<li><a href="online_category.php?main_category=의류&sub_category=하의">하의</a></li>
							<li><a href="online_category.php?main_category=의류&sub_category=신발">신발</a></li>
							<li><a href="online_category.php?main_category=의류&sub_category=기타">기타</a></li>
						</ul>
					</li>
					<li><a href="#가방">가방</a>
						<ul class="sub">
							<li><a href="online_category.php?main_category=가방&sub_category=백팩">백팩</a></li>
							<li><a href="online_category.php?main_category=가방&sub_category=크로스/숄더백">크로스/숄더백</a></li>
							<li><a href="online_category.php?main_category=가방&sub_category=토트백">토트백</a></li>
							<li><a href="online_category.php?main_category=가방&sub_category=클러치">클러치</a></li>
							<li><a href="online_category.php?main_category=가방&sub_category=기타">기타</a></li>
						</ul>
					</li>
          			<li><a href="#잡화">잡화</a>
						<ul class="sub">
							<li><a href="online_category.php?main_category=잡화&sub_category=악세사리">악세사리</a></li>
							<li><a href="online_category.php?main_category=잡화&sub_category=지갑">지갑</a></li>
							<li><a href="online_category.php?main_category=잡화&sub_category=케이스">케이스</a></li>
							<li><a href="online_category.php?main_category=잡화&sub_category=기타">기타</a></li>
						</ul>
					</li>
          			<li><a href="#화장품">화장품</a>
						<ul class="sub">
							<li><a href="online_category.php?main_category=화장품&sub_category=기초">기초</a></li>
							<li><a href="online_category.php?main_category=화장품&sub_category=기타">기타</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</header>
	</div>

	<form action="online_search.php" method="GET">
		<div class="search-box">
			<select name="search-type">
				<option value="name">상품명</option>
				<option value="siteName">사이트명</option>
			</select>
			<input type="text" name="search" placeholder="검색어를 입력하세요">
			<button><img src="../img/검색.png"></button>
		</div>
  	</form>
	
	<div class="order">
	<form action="online_order.php" method="GET">
			<select name="order">
				<option value="siteName">사이트명</option>
				<option value="name">상품명</option>
			</select>
			<select name="range">
				<option value="ASC">오름차순</option>
				<option value="DESC">내림차순</option>
			</select>
			<button>정렬</button>
	</form>
	</div>
	

	<div class="product">
		<?php                           
			$sql = mq("SELECT * FROM online_info ORDER BY $order $range");

			// -------------- 페이징
			$total_record = mysqli_num_rows($sql); //상품 총 수
			$list = 8; //보여줄 상품 수
			$block_cnt = 5; //페이지 수
			$block_num = ceil($page / $block_cnt); //현재 페이지 블록
			$block_start = (($block_num - 1) * $block_cnt) + 1;
			$block_end = $block_start + $block_cnt - 1;
			$total_page = ceil($total_record / $list);

			if($block_end > $total_page) {
				$block_end = $total_page; 
			}

			$total_block = ceil($total_page / $block_cnt); //블록의 총 개수
			$page_start = ($page - 1) * $list; //페이지 시작

			
			//게시물 목록               
			$sql2 = mq("SELECT * FROM online_info ORDER BY $order $range LIMIT $page_start, $list");
			
			while($row = mysqli_fetch_array($sql2)){
				$name = $row["name"];
				                                                                
    	?>
		
		<div class="item" style="text-align: center;">
			<a onclick= "updateClick2('<?= $name ?>')">
				<img src="<?= $row["img"]?>">
			</a>
			<a style="font-size: 16px; color: #186325; font-weight: bold;"><br><?= $row["siteName"]?></a>
			<a style="font-size: 14px; color: #555;"><br><?= $name ?></a>
			<a style="font-size: 16px; color: #186325;"><br><?= $row["price"]?></a>
		</div>
	
		<?php                
        	}                                                                                                            
        ?>

	</div>

		<!-- 하단 페이징 부분 -->
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<?php
					if ($page <= 1){ } 
					else {
						if(isset($unum)){
							echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=1' aria-label='Previous'>처음</a></li>";
						} else {
							echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=1' aria-label='Previous'>처음</a></li>";
						}
					}
									
					if ($page <= 1){ } 
					else {
						$pre = $page - 1;
						if(isset($unum)){
							echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=$pre'>◀&nbsp이전</a></li>";
						} else {
							echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=$pre'>◀&nbsp이전</a></li>";
						}
					}
									
					for($i = $block_start; $i <= $block_end; $i++){
						if($page == $i){
							echo "<li class='page-item'><a class='page-link' disabled><b style='color: #df7366;'>$i</b></a></li>";
						} else {
							if(isset($unum)){
								echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=$i'>$i</a></li>";
							} else {
								echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=$i'>$i</a></li>";
							}
						}
					}
									
					if($page >= $total_page){ } 
					else {
						$next = $page + 1;
						if(isset($unum)){
							echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=$next'>다음&nbsp▶</a></li>";
						} else {
							echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=$next'>다음&nbsp▶</a></li>";
						}
					}
								
					if($page >= $total_page){ } 
					else {
						if(isset($unum)){
							echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=$total_page'>마지막</a>";
						} else {
							echo "<li class='page-item'><a class='page-link' href='online_order.php?order=$order&range=$range&page=$total_page'>마지막</a>";
						}
					}
				?>                                        
			</ul>                                                                  
		</nav>
  </body>
</html>