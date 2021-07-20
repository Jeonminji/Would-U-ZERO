const modalBtn = document.querySelector(".off_box7");
const modal = document.querySelector(".modal");
const overlay = modal.querySelector('.modal_overlay');

const openModal = () => {
    modal.classList.remove("hidden");
}

const closeModal = () =>{
    modal.classList.add("hidden");
}

modalBtn.addEventListener('click',openModal);
overlay.addEventListener('click',closeModal);