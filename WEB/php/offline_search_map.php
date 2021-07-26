<?php
$link=mysqli_connect("localhost","admin","admin","test");
$filter = $_GET['filter'];
$page = $_GET['page'];
$sql = "select * from offline_info where store_type like '%$filter%'";
$result = mysqli_query($link, $sql);
$html = array();
$data = array();
$i = 0;

while ($row = mysqli_fetch_array($result))
{
	array_push($data, [
		"store_name" => $row['store_name'],
		"address" => $row['address'],
		"store_type" => $row['store_type'],
		"lat" => $row['lat'],
		"lng" => $row['lng']
	]);

}

echo(json_encode($data, JSON_UNESCAPED_UNICODE));
?>