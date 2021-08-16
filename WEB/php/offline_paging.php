<?php
	$filter = $_GET['filter'];
	$page = $_GET['page'];
	$link = mysqli_connect("localhost", "admin", "admin", "WZ");
	$sql = "select store_name from offline_info where store_type like '%$filter%'";
	$result = mysqli_query($link, $sql);
	$i = 0;
	$count = 0;

	while (	$row = mysqli_fetch_row($result))
	{
		$i = $i + 1;
	}

	if ($i % 3 == 0)
		$count = $i / 3;
	else
		$count = floor($i / 3 + 1);

	$margin = 0;

	if ($count == 1)
		$margin = 120;
	elseif ($count == 3)
		$margin = 50;
	else
		$margin = 40;

	echo('<div class="page">');
	for($i = 0; $i < $count; $i = $i + 1)
	{
		echo('<a style="margin-left: '.$margin.'px;" href="?filter='.$filter.'&page='.($i + 1).'">'.($i + 1).'</a>');
	}
	echo('</div>');
?>