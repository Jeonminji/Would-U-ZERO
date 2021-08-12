<?php
    header("Content-Type: application/json");
    $link = mysqli_connect("localhost", "admin", "admin", "WZ");
    
    if(isset($_GET['store_name'])){
        $store_name =mysqli_real_escape_string($link, $_GET['store_name']);
       
    }
    

    $query = "UPDATE online_click SET click_count = click_count+1 WHERE product_name = '{$store_name}'";
    $result=mysqli_query($link, $query);
    

    $query2 = "SELECT * FROM online_info WHERE online_info.name = '{$store_name}'";
    $result2=mysqli_query($link, $query2);
    while($row=mysqli_fetch_array($result2)){

       $data = array(
              "link" => $row['link']            
       );
      
    }
    echo(json_encode($data,JSON_UNESCAPED_UNICODE));
?>