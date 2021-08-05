<?php 
    $link = mysqli_connect("localhost", "admin", "admin", "WZ");

    $query = "SELECT * from online_info join online_click on online_info.name = online_click.product_name ORDER BY online_click.click_count desc limit 8";
    $recommend_items = '';
    $result = mysqli_query($link, $query);

    while($row = mysqli_fetch_array($result)){
        $name = $row['name'];
        $price_string_count = strlen($row['price']);
        if($price_string_count > 6){
          $price = substr_replace($row['price'],',',-6,0);
        }
        else{
          $price = $row['price'];
        }
        
        $recommend_items .= '<li class = "rec_item">
                              <div class = "item_box">
                                <div class = "slide_img_box">
                                  <img src = "'.$row['img'].'" alt = "1">
                                  <div class = "overlay">
                                
                                  <a onclick = "updateClick(\''.$row['name'].'\')" class = "buy_btn">Buy Now</a>
                                    
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