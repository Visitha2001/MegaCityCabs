    const slider = document.getElementById('slider');
    const slides = document.querySelectorAll('.slide');
    const navButtons = document.querySelectorAll('.slider-nav button');
    let currentIndex = 0;

    function updateSlider(index) {
      slider.style.transform = `translateX(-${index * 100}%)`;
      navButtons.forEach((btn, i) => {
        btn.classList.toggle('active', i === index);
      });
    }

    function nextSlide() {
      currentIndex = (currentIndex + 1) % slides.length;
      updateSlider(currentIndex);
    }

    setInterval(nextSlide, 5000);

    navButtons.forEach(button => {
      button.addEventListener('click', () => {
        currentIndex = parseInt(button.getAttribute('data-index'));
        updateSlider(currentIndex);
      });
    });