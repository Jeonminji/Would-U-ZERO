function isElementUnderBottom(scroll_div, triggerDiff) {
    const { top } = scroll_div.getBoundingClientRect();
    const { innerHeight } = window;
    return top > innerHeight + (triggerDiff || 0);
  }
  
  function upScroll() {
    const scroll_divs = document.querySelectorAll('.up-on-scroll');
    scroll_divs.forEach(scroll_div => {
      if (isElementUnderBottom(scroll_div, -20)) {
        scroll_div.style.opacity = "0";
        scroll_div.style.transform = 'translateY(70px)';
      } else {
        scroll_div.style.opacity = "1";
        scroll_div.style.transform = 'translateY(0px)';
      }
    })
  }
  
  window.addEventListener('scroll', upScroll);