<?php 
    $link = mysqli_connect("localhost", "admin", "admin", "test");

    $query = "SELECT * from online_test join click_online on online_test.name = click_online.product_name ORDER BY click_count desc limit 8";
    $items = '';
    $result = mysqli_query($link, $query);

    while($row = mysqli_fetch_array($result)){
        $name = $row['name'];
        $price = substr_replace($row['price'],',',-6,0);
        $items .= '<li class = "rec_item">
                      <div class = "item_box">
                        <div class = "slide_img_box">
                          <img src = "'.$row['img'].'" alt = "1">
                          <div class = "overlay">
                        
                          <a onclick = "info(\''.$row['name'].'\')" class = "buy_btn">Buy Now</a>
                            
                          </div>
                        </div>
                        <div class = "item_detail_box">
                          <div class = "type">
                            <div class = "store_name">'.$row['siteName'].'</div>
                            <div class = "item_name">'.$row['name'].'</div>
                            <div class = "item_price">'.$price.'</div>
                          </div>
                        </div>
                      </div>
                    </li>';
        
    }


?>