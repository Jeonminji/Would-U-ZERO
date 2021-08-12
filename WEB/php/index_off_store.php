<?php
    $link = mysqli_connect("localhost", "admin", "admin", "WZ");

    $query= "SELECT * from offline_info";
    $article = '';
    $result=mysqli_query($link, $query);

    while($row=mysqli_fetch_array($result)){
        $article .= '<form  method = "POST" name = "test">
        <div class = "off_store"><span>
        <input type = "button" class = "offline_btn" name = "store_name" onclick = "info(this.value)" value = "'.$row['store_name'].'" ></span></div></form>';    
    }

?>

