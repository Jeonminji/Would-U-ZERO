const modalBtn = document.querySelector(".off_box7");
const modal = document.querySelector(".modal");
const overlay = modal.querySelector('.modal_overlay');
var off_store_name = document.querySelector('.off_store_name').querySelector('span');
var modal_img1 = document.querySelector('.slide01').querySelector('img');
var modal_img2 = document.querySelector('.slide02').querySelector('img');
var modal_img3 = document.querySelector('.slide03').querySelector('img');
var modal_store_name = document.querySelector('.modal_name');
var modal_insta = document.querySelector('.modal_instargram').querySelector('a');
var modal_insta_txt = document.querySelector('.instargram_txt');
var modal_address = document.querySelector('.modal_address_txt');
var modal_opening_hours = document.querySelector('.modal_opening_hours_txt');
var modal_store_num = document.querySelector('.modal_number_txt');
var icon = document.querySelector('.more_info_contents').querySelectorAll('i');
var icon_num = icon.length;
var modal_etc = document.querySelector('.modal_etc');
var modal_etc_txt = document.querySelector('.modal_etc_txt');
var modal_info_divs = document.querySelector(".more_info_contents").querySelectorAll('div');
var modal_info_divs_num = modal_info_divs.length;

function modalinfo(store){      
    
    $.ajax({
       type : "POST",
       url : "php/index_off_modal.php",
       data: {store_name:store},
       dataType : "json",
       error : function(){ alert('통신실패!!');  },
       success : function(res){ 
            var lat = res.lat;
            var lng = res.lng;
            modal_maps(lat, lng);
            modal_img1.src = res.img1;
            modal_img2.src = res.img2;
            modal_img3.src = res.img3;
            img_makeClone();

            modal_store_name.innerText = res.store_name;
            modal_insta.href = res.link;
            modal_address.innerText = res.address;
            modal_opening_hours.innerText = res.opening_hours;
            modal_store_num.innerText = res.store_num;

            if(res.other_info != '" "'){
                modal_etc.classList.remove("hidden");
                modal_etc_txt.innerHTML = res.other_info;
                if(imgWidth <= 300){
                    for(i = 0; i < icon_num; i++){
                        icon[i].style.fontSize = '1em';
                    }
                    for(i = 0; i < modal_info_divs_num; i++){
                        modal_info_divs[i].style.height = "30px"
                    }
                    modal_address.style.fontSize = '0.8em';
                    modal_insta_txt.style.fontSize = '0.8em';
                    modal_opening_hours.style.fontSize = '0.8em';
                    modal_store_num.style.fontSize = '0.8em';
                    modal_etc_txt.style.fontSize = '0.8em';
                }
                else{
                    for(i = 0; i < icon_num; i++){
                        icon[i].style.fontSize = '1.5em';
                    }
                    for(i = 0; i < modal_info_divs_num; i++){
                        modal_info_divs[i].style.height = "40px"
                    }
                    modal_address.style.fontSize = '1em';
                    modal_insta_txt.style.fontSize = '1em';
                    modal_opening_hours.style.fontSize = '1em';
                    modal_store_num.style.fontSize = '1em';
                    modal_etc_txt.style.fontSize = '1em';
                }
            
            }
            else{
                modal_etc.classList.add("hidden");
                
                if(imgWidth <= 300){
                    for(i = 0; i < icon_num; i++){
                        icon[i].style.fontSize = '1em';
                    }
                    for(i = 0; i < modal_info_divs_num; i++){
                        modal_info_divs[i].style.height = "50px"
                    }
                    modal_address.style.fontSize = '1em';
                    modal_insta_txt.style.fontSize = '1em';
                    modal_opening_hours.style.fontSize = '1em';
                    modal_store_num.style.fontSize = '1em';
                }
                else{
                    for(i = 0; i < icon_num; i++){
                        icon[i].style.fontSize = '1.5em';
                    }
                    for(i = 0; i < modal_info_divs_num; i++){
                        modal_info_divs[i].style.height = "50px"
                    }
                    modal_address.style.fontSize = '1.5em';
                    modal_insta_txt.style.fontSize = '1.5em';
                    modal_opening_hours.style.fontSize = '1.5em';
                    modal_store_num.style.fontSize = '1.5em';
                }
            }
        }
    });
}

const openModal = () => {
    modal.classList.remove("hidden");
    modalinfo(off_store_name.innerText);
    
}

const closeModal = () =>{
    imgDelete();
    modal.classList.add("hidden");
    for(i = 0; i < icon_num; i++){
        icon[i].classList.remove("fa-2x");
    }
    
    for(i = 0; i < icon_num; i++){
        icon[i].classList.remove("fa-x");
    }
}

modalBtn.addEventListener('click',openModal);
overlay.addEventListener('click',closeModal);
