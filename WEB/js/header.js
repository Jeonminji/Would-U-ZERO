var toogleBtn = document.querySelector('.navbar_toogleBtn');
var header_category = document.querySelector('.navbar_category');

toogleBtn.addEventListener('click',()=>{
    header_category.classList.toggle('active');
});