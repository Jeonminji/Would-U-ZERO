var modal_img_box = document.querySelector('.modal_slide_list');
var modal_imgs= document.querySelectorAll('.modal_slide_content');
var modal_imgIdx=0;
var modal_img_count = modal_imgs.length;

var img_prevBtn = document.querySelector('.modal_img_prev');
var img_nextBtn = document.querySelector('.modal_img_next');

var off_map_size = document.querySelector('.off_map').clientWidth;
if(off_map_size == 890){
    var imgWidth = 500;
}
else if(off_map_size == 560){
    var imgWidth = 300;
}
else{
    var imgWidth = 250;
}

// 무한 슬라이드로 만들기 위한 클론
function img_makeClone(){
    for(var i = 0; i < modal_img_count; i++){

        var cloneImg = modal_imgs[i].cloneNode(true);
        cloneImg.classList.add('clone');

        modal_img_box.appendChild(cloneImg);
    }

    for(var i = modal_img_count - 1; i >= 0; i--){

        var cloneImg = modal_imgs[i].cloneNode(true);
        cloneImg.classList.add('clone');

        modal_img_box.prepend(cloneImg);
    }

    updateImgWidth();
    setImgInitialPos();

    setTimeout(function(){
        modal_img_box.classList.add('animated');
    }, 100);
}

function imgDelete(){
    $('li').remove('.modal_slide_content.clone');
}

// width item 개수의 따라 변경
function updateImgWidth(){
    var currentImgs = document.querySelectorAll('.modal_slide_content');
    var newImgCount = currentImgs.length;

    var newImgWidth = imgWidth*newImgCount+'px';
    modal_img_box.style.width = newImgWidth;
}
// 첫 화면상이 클론이 아닌 상품이 보여지게
function setImgInitialPos(){
    var img_initialTranslateValue = -imgWidth * modal_img_count;

    modal_img_box.style.transform = 'translateX('+img_initialTranslateValue+'px)';
}

img_nextBtn.addEventListener('click',function(){
    imgSlide(modal_imgIdx+1);
})
img_prevBtn.addEventListener('click',function(){
    imgSlide(modal_imgIdx-1);
})
// 버튼 클릭 시 move함수
function imgSlide(num){
    
    modal_img_box.style.left = -num * imgWidth + 'px';
    modal_imgIdx = num;

    if(modal_imgIdx == modal_img_count || modal_imgIdx == -modal_img_count){
        setTimeout(function(){
            modal_img_box.classList.remove('animated');
            modal_img_box.style.left = '0px';
            modal_imgIdx = 0;
        },500);

        setTimeout(function(){
            modal_img_box.classList.add('animated');   
        },600);

    }
}

