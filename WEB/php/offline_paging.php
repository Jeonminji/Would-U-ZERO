<?php
	$filter = $_GET['filter'];
	$page = $_GET['page'];
	$link=mysqli_connect("localhost","admin","admin","test");
	$sql = "select store_name from offline_info where store_type like '%$filter%'";
	$result = mysqli_query($link, $sql);
	$i = 0;
	$count = 0;

	while (	$row = mysqli_fetch_row($result))
	{
		$i = $i + 1;
	}

	if ($i % 3 == 0)
	{
		$count = $i / 3;
	}
	else
	{
		$count = floor($i / 3 + 1);
	}

	for($i = 0; $i < $count; $i = $i + 1)
	{
		echo('<a href="?filter='.$filter.'&page='.($i + 1).'">'.($i + 1).'</a>');
	}
?>