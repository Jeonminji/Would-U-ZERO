var slides = document.querySelector('.slides');
var slides_outline = document.querySelector('.rec_item_box3');
var slide = document.querySelectorAll('.rec_item');
var currentIdx=0;
var slideCount = slide.length;

var prevBtn = document.querySelector('.prev');
var nextBtn = document.querySelector('.next');

var slideWidth = 300;
var slideMargin = 20;

makeClone();

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

function updateWidth(){
    var currentSlides = document.querySelectorAll('.rec_item');
    var newSlideCount = currentSlides.length;

    var newWidth = (slideWidth + slideMargin)*newSlideCount+'px';
    slides.style.width = newWidth;
}

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
