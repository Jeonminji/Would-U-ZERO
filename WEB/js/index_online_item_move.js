var online_slides = document.querySelector('.online_slides');
var online_slides2 = document.querySelector('.online_slides2');

var onlineslides_outline = document.querySelector('.online_item_box3');

var online_slide = online_slides.querySelectorAll('.online_item');
var online_slide2 = online_slides2.querySelectorAll('.online_item')
var online_currentIdx=0;
var online_slideCount = online_slide.length;

var online_prevBtn = document.querySelector('.online_prev');
var online_nextBtn = document.querySelector('.online_next');

var online_slideWidth = 300;
var online_slideMargin = 20;

online_makeClone();
function online_makeClone(){
    for(var i = 0; i < online_slideCount; i++){

        var online_cloneSlide = online_slide[i].cloneNode(true);
        online_cloneSlide.classList.add('clone');

        var online_cloneSlide2 = online_slide2[i].cloneNode(true);
        online_cloneSlide2.classList.add('clone');

        online_slides.appendChild(online_cloneSlide);
        online_slides2.appendChild(online_cloneSlide2);
    }

    for(var i = online_slideCount-1; i >= 0; i--){

        var online_cloneSlide = online_slide[i].cloneNode(true);
        online_cloneSlide.classList.add('clone');

        var online_cloneSlide2 = online_slide2[i].cloneNode(true);
        online_cloneSlide2.classList.add('clone');

        online_slides.prepend(online_cloneSlide);
        online_slides2.prepend(online_cloneSlide2);
    }

    online_updateWidth();
    online_setInitialPos();

    setTimeout(function(){
        online_slides.classList.add('animated');
        online_slides2.classList.add('animated');
    }, 100);
}

// width online_item 개수의 따라 변경
function online_updateWidth(){
    var online_currentSlides = online_slides.querySelectorAll('.online_item');
    var online_currentSlides2 = online_slides2.querySelectorAll('.online_item');

    var online_newSlideCount = online_currentSlides.length;
    var online_newSlideCount2 = online_currentSlides2.length;

    var online_newWidth = (online_slideWidth + online_slideMargin)*online_newSlideCount+'px';
    var online_newWidth2 = (online_slideWidth + online_slideMargin)*online_newSlideCount2+'px';

    online_slides.style.width = online_newWidth;
    online_slides2.style.width = online_newWidth2;
}

// 첫 화면상이 클론이 아닌 상품이 보여지게
function online_setInitialPos(){
    var online_initialTranslateValue = -(online_slideWidth + online_slideMargin) * online_slideCount;
    
    online_slides.style.transform = 'translateX('+online_initialTranslateValue+'px)';
    online_slides2.style.transform = 'translateX('+online_initialTranslateValue+'px)';
}

online_nextBtn.addEventListener('click',function(){
    online_moveSlide(online_currentIdx+1);
})
online_prevBtn.addEventListener('click',function(){
    online_moveSlide(online_currentIdx-1);
})

// 버튼 클릭 시 move함수
function online_moveSlide(num){
    online_slides.style.left = -num * (online_slideWidth + online_slideMargin) + 'px';
    online_slides2.style.left = -num * (online_slideWidth + online_slideMargin) + 'px';
    online_currentIdx = num;

    if(online_currentIdx == online_slideCount || online_currentIdx == -online_slideCount){
        setTimeout(function(){
            online_slides.classList.remove('animated');
            online_slides2.classList.remove('animated');
            online_slides.style.left = '0px';
            online_slides2.style.left = '0px';
            online_currentIdx = 0;
        },500);

        setTimeout(function(){
            online_slides.classList.add('animated');
            online_slides2.classList.add('animated');   
        },600);

    }
}

// 아래 코드는 n초마다 한번씩 상품 자동 이동 && 마우스 hover시 n초 이동 멈춤 && 마우스 벗어나면 다시 이동
var timer2 = undefined;

function online_autoSlide(){
    if(timer2 == undefined){
        timer2= setInterval(function(){
            online_moveSlide(online_currentIdx + 1);
        }, 3000);
    }
}

autoSlide(); //3초마다 한 번씩 상품 하나 씩 이동

function online_stopSlide(){
    clearInterval(timer2);
    timer2 = undefined;
}

onlineslides_outline.addEventListener('mouseenter', function(){
    online_stopSlide();
});

onlineslides_outline.addEventListener('mouseleave', function(){
    online_autoSlide();
});
