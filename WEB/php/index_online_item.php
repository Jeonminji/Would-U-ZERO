<?php 
    $link = mysqli_connect("localhost", "admin", "admin", "WZ");

    $query = "SELECT * from online_info order by rand() limit 24";
    $online_items = '';
    $online_items2 = '';
    $result = mysqli_query($link, $query);

    for($i = 0; $i<12; $i++){
      $row = mysqli_fetch_array($result);
      $price_string_count = strlen($row['price']);
      if($price_string_count > 6){
        $price = substr_replace($row['price'],',',-6,0);
      }
      else{
        $price = $row['price'];
      }
        
      $online_items .= '<li class = "online_item">
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
    
    for($i = 0; $i<12; $i++){
      $row = mysqli_fetch_array($result);
      $price_string_count = strlen($row['price']);
      if($price_string_count > 6){
        $price = substr_replace($row['price'],',',-6,0);
      }
      else{
        $price = $row['price'];
      }
      $online_items2 .= '<li class = "online_item">
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