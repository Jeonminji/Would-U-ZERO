<?php
	$name = $_POST['store_name'];
	$link = mysqli_connect("localhost", "admin", "admin", "WZ");
	$sql = "SELECT * FROM offline_info where store_name like '%$name';";
	$result = mysqli_query($link, $sql);	

	$row = mysqli_fetch_array($result);

	echo(json_encode($row, JSON_UNESCAPED_UNICODE));
?>