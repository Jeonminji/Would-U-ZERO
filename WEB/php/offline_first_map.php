<?php
      $link=mysqli_connect("localhost","admin","admin","test");
      $sql = "SELECT * FROM offline_info;";
      $result = mysqli_query( $link, $sql );
      $i = 0;
      $data = array();

      while ($row = mysqli_fetch_array($result)) {
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


