<?php
$db_host = "localhost";
$db_user = "admin";
$db_pw = "admin";
$db_name = "";
$link = mysqli_connect($db_host, $db_user, $db_pw, $db_name);
if(mysqli_connect_error($link)){
	echo "MAriaDB conncetion Failed!!","<br>";
	echo "error: ", mysqli_connect_error();
}else{
	echo "Mariadb connetion Success!!";
}

mysqli_close($link);

?>
