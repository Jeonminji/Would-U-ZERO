<?php
	// ini_set('display_errors', '0'); //에러 메시지 출력 x
    $db_host = "localhost"; 
    $db_user = "admin";
    $db_passwd = "admin";
    $db_name = "Zero";
    header("Content-Type:text/html;charset=utf-8");

    $link = mysqli_connect($db_host, $db_user, $db_passwd, $db_name); //DB 접속

	$query = "
		SELECT 
			main_category, sub_category, siteName, name, price, img, link
		FROM
			online
		ORDER BY name DESC
		LIMIT 1, 8
	";

	$result = mysqli_query($link, $query);

	$article = '';
	while($row = mysqli_fetch_array($result)) {
		$article .= '<div class = item>';
		$article .= '<a href="'.$row['link'].'">';
		$article .= '<img src="'.$row['img'].'">';
		$article .= '</a>';
		$article .= '<br>사이트명: '.$row['siteName'].'<br>';
		$article .= '물품명: '.$row['name'].'<br>';
		$article .= '가격: '.$row['price'].'<br>';
		$article .= '</div>';
	}

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
      <link rel="stylesheet" href="css/header.css?after">
      <link rel="stylesheet" href="css/category.css?after">
  </head>
  <body>
    <!-- header -->
    <div class="container">
    <header>
      <div class="header_logo">
        <!-- <img src="" width="" /> -->
        <a href="">WOULD U ZERO?</a>
      </div>

      <div class="topnav">
        <ul>
          <li><a href="index.html">home</a></li>
          <li><a href="about.html">about</a></li>
          <li><a href="online.php">online</a></li>
          <li><a href="offline.html">offline</a></li>
        </ul>
      </div>
    </header>
  </div>
  <!-- header end -->

  <style>
	  .search-box {
		  	margin-top: 15px;
		  	margin-left: 300px;
		  	height: 40px;
        	width: 1000px;
            border: 1px solid #186325;
            background: #ffffff;
	  }
	  .search-box > input {
			height: 35px;
			width: 740px;
			font-size: 16px;
			padding: 10px;
			border: 0px;
			outline: none;
			float: left;
	  }
	  .search-box > button {
			width: 50px;
			height: 100%;
            border: 0px;
            background: #186325;
            outline: none;
            float: right;
            color: #ffffff
	  }

	  .product {
		  margin-top: 0px;
		  margin-left: 260px;
	  }

	  .item {
		  margin: 20px 0 0 40px;
		  outline-style: solid;
		  height: 330px;
		  width: 220px;
		  float: left;
	  }

	  .item > a > img {
		  height: 240px;
		  width: 220px;
	  }

	  select {
		  margin-top: 15px;
		  margin-left: 250px;
		  width: 140px;
	  }
  </style>
  
  <div class="search-box">
	<input type="text" placeholder="검색어를 입력하세요">
	<button>검색</button>
  </div>

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
					<li><a href="#">주방</a>
						<ul class="sub">
							<li><a href="#세제">세제</a></li>
							<li><a href="#수세미">수세미</a></li>
							<li><a href="#행주">행주</a></li>
							<li><a href="#솔">솔</a></li>
							<li><a href="#기타">기타</a></li>
						</ul>
					</li>
					<li><a href="#bathroom">욕실</a>
						<ul class="sub">
							<li><a href="#헤어">헤어</a></li>
							<li><a href="#바디">바디</a></li>
							<li><a href="#구강">구강</a></li>
							<li><a href="#기타">기타</a></li>
						</ul>
					</li>
					<li><a href="#clothes">의류</a>
						<ul class="sub">
							<li><a href="#상의">상의</a></li>
							<li><a href="#하의">하의</a></li>
							<li><a href="#신발">신발</a></li>
							<li><a href="#기타">기타</a></li>
						</ul>
					</li>
					<li><a href="#bag">가방</a>
						<ul class="sub">
							<li><a href="#백팩">백팩</a></li>
							<li><a href="#크로스/숄더백">크로스/숄더백</a></li>
							<li><a href="#토트백">토트백</a></li>
							<li><a href="#클러치">클러치</a></li>
							<li><a href="#기타">기타</a></li>
						</ul>
					</li>
          			<li><a href="#etc">잡화</a>
						<ul class="sub">
							<li><a href="#악세사리">악세사리</a></li>
							<li><a href="#지갑">지갑</a></li>
							<li><a href="#케이스">케이스</a></li>
							<li><a href="#기타">기타</a></li>
						</ul>
					</li>
          			<li><a href="#cosmetics">화장품</a>
						<ul class="sub">
							<li><a href="#기초">기초</a></li>
							<li><a href="#색조">색조</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</header>
	</div>

	<select name="range">
        <option value="">낮은가격순</option>
        <option value="">높은가격순</option>
		<option value="ASC">사이트명 오름차순</option>
		<option value="DESC">사이트명 내림차순</option>
    </select>
	
	<div class="product">
		<?= $article ?>
	</div>
  </body>
</html>
