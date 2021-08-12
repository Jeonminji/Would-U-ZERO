<?php
	$filter = $_GET['filter'];
	$link=mysqli_connect("localhost","admin","admin","test");
	$sql = "SELECT store_type, store_name FROM offline_info where store_type like '%$filter%';";
	$result = mysqli_query($link, $sql);	
	$i = 0;
	while (	$row = mysqli_fetch_row($result))
	{
		$i = $i + 1;
	}
	echo($i);
?>