<?php
    header("Content-Type: application/json");
    $link = mysqli_connect("localhost", "admin", "admin", "WZ");
    
    if(isset($_POST['store_name'])){
        $store_name =mysqli_real_escape_string($link, $_POST['store_name']);
       
    }else{
        $query = "select store_name from offline_info limit 1";
        $result=mysqli_query($link, $query);
        while($row=mysqli_fetch_array($result)){
            $store_name = $row['store_name'];
        }
    }

    $query = "SELECT * from offline_info where store_name = '{$store_name}'";
    $result=mysqli_query($link, $query);
    
    while($row=mysqli_fetch_array($result)){

       $data = array(
              "store_name" => $row['store_name'],
              "address" => $row['address'],
              "store_type" => $row['store_type'],
              "lat" => $row['lat'],
              "lng" => $row['lng']              
       );
    }
    echo(json_encode($data,JSON_UNESCAPED_UNICODE));
?>
