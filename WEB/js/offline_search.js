var slideIndex = [1,1,1];
var slideId = ["mySlides1", "mySlides2", "mySlides3"];

function plusSlides(n, no) {
  showSlides(slideIndex[no] += n, no);
}

function showSlides(n, no) {
  var i;
  var x = document.getElementsByClassName(slideId[no]);
  if (n > x.length) {slideIndex[no] = 1}
  if (n < 1) {slideIndex[no] = x.length}
  for (i = 0; i < x.length; i++) {
	 x[i].style.display = "none";  
  }
  x[slideIndex[no]-1].style.display = "block";  
}

// modal part
function openModal() {
	document.getElementById("myModal").style.display = "block";
}
  
function closeModal() {
  document.getElementById("myModal").style.display = "none";
}

// map
var map, marker, infowindow;

function makeOverListener(map, marker, infowindow) {
	return function() {
		infowindow.open(map, marker);
	};
  }

  function makeOutListener(infowindow) {
	return function() {
		infowindow.close();
	};
  }