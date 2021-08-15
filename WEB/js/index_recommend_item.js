var slides = document.querySelector('.slides');
var slides_outline = document.querySelector('.rec_item_box3');
var slide = document.querySelectorAll('.rec_item');
var currentIdx=0;
var slideCount = slide.length;

var prevBtn = document.querySelector('.prev');
var nextBtn = document.querySelector('.next');

var slideWidth = slide[0].clientWidth;
var slideMargin = 20;

makeClone();
// 무한 슬라이드로 만들기 위한 클론
function makeClone(){
    for(var i = 0; i < slideCount; i++){

        var cloneSlide = slide[i].cloneNode(true);
        cloneSlide.classList.add('clone');

        slides.appendChild(cloneSlide);
    }

    for(var i = slideCount-1; i >= 0; i--){

        var cloneSlide = slide[i].cloneNode(true);
        cloneSlide.classList.add('clone');

        slides.prepend(cloneSlide);
    }
    updateWidth();
    setInitialPos();

    setTimeout(function(){
        slides.classList.add('animated');
    }, 100);
}
// width item 개수의 따라 변경
function updateWidth(){
    var currentSlides = document.querySelectorAll('.rec_item');
    var newSlideCount = currentSlides.length;

    var newWidth = (slideWidth + slideMargin)*newSlideCount+'px';
    slides.style.width = newWidth;
}
// 첫 화면상이 클론이 아닌 상품이 보여지게
function setInitialPos(){
    var initialTranslateValue = -(slideWidth + slideMargin) * slideCount;

    slides.style.transform = 'translateX('+initialTranslateValue+'px)';
}

nextBtn.addEventListener('click',function(){
    moveSlide(currentIdx+1);
})
prevBtn.addEventListener('click',function(){
    moveSlide(currentIdx-1);
})
// 버튼 클릭 시 move함수
function moveSlide(num){
    slides.style.left = -num * (slideWidth + slideMargin) + 'px';
    currentIdx = num;

    if(currentIdx == slideCount || currentIdx == -slideCount){
        setTimeout(function(){
            slides.classList.remove('animated');
            slides.style.left = '0px';
            currentIdx = 0;
        },500);

        setTimeout(function(){
            slides.classList.add('animated');   
        },600);

    }
}

// 아래 코드는 n초마다 한번씩 상품 자동 이동 && 마우스 hover시 n초 이동 멈춤 && 마우스 벗어나면 다시 이동
var timer = undefined;

function autoSlide(){
    if(timer == undefined){
        timer = setInterval(function(){
            moveSlide(currentIdx + 1);
        }, 3000);
    }
}

autoSlide(); //3초마다 한 번씩 상품 하나 씩 이동

function stopSlide(){
    clearInterval(timer);
    timer = undefined;
}

slides_outline.addEventListener('mouseenter', function(){
    stopSlide();
});

slides_outline.addEventListener('mouseleave', function(){
    autoSlide();
});


