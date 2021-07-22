<?php
    header("Content-Type: application/json");
    $link=mysqli_connect("localhost","admin","admin","test");
    
    if(isset($_GET['store_name'])){
        $store_name =mysqli_real_escape_string($link, $_GET['store_name']);
       
    }
    

    $query = "UPDATE click_online SET click_count = click_count+1 WHERE product_name = '{$store_name}'";
    $result=mysqli_query($link, $query);
    

    $query2 = "SELECT * FROM online_test WHERE online_test.name = '{$store_name}'";
    $result2=mysqli_query($link, $query2);
    while($row=mysqli_fetch_array($result2)){

       $data = array(
              "link" => $row['link']            
       );
      
    }
    echo(json_encode($data,JSON_UNESCAPED_UNICODE));
?>