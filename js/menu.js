document.addEventListener('DOMContentLoaded', function () {
    const btnMobile = document.getElementById('btn-mobile');
    const nav = document.getElementById('nav');
    const produtos = document.getElementById('produtos');
  
    // Função para abrir/fechar o menu mobile
    function toggleMenu(event) {
      if (event.type === 'touchstart') event.preventDefault();
      nav.classList.toggle('active');
      const active = nav.classList.contains('active');
      btnMobile.setAttribute('aria-expanded', active);
      if (active) {
        btnMobile.setAttribute('aria-label', 'Fechar Menu');
      } else {
        btnMobile.setAttribute('aria-label', 'Abrir Menu');
      }
    }
  
    // Previne o comportamento padrão do link "Produtos" no mobile, mas permite os links do submenu
    function toggleSubMenu(event) {
      const submenu = produtos.querySelector('.submenu');
  
      if (event.target.closest('a') && event.target.closest('.submenu')) {
        return; // Permite o clique em links dentro do submenu
      }
      
      event.preventDefault(); // Impede a navegação imediata
      submenu.classList.toggle('active');
    }
  
    btnMobile.addEventListener('click', toggleMenu);
    btnMobile.addEventListener('touchstart', toggleMenu);
  
    produtos.addEventListener('click', toggleSubMenu);
    produtos.addEventListener('touchstart', toggleSubMenu);
  });
  