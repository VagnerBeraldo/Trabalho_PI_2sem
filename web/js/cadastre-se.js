document.getElementById('id-cadastrese').addEventListener('click', function() {
     
    
    // Seleciona os elementos para ocultar
     document.querySelector('.section-boasVindas').style.display = 'none';
     document.querySelector('.sobre').style.display = 'none';
     document.querySelector('.section-vagas').style.display = 'none';
     document.querySelector('.section-1').style.display = 'none';
     document.querySelector('.section-2').style.display = 'none';
     document.querySelector('.section-3').style.display = 'none';
     document.getElementById('depCliente').style.display = 'none';
     document.querySelector('.depoimento').style.display = 'none';
     // Exibe o iframe de cadastro
     document.getElementById('id-formCadastro').style.display = 'block';
    
    //document.getElementById('nav').style.display = 'none';  
    //document.querySelector('.nav ul li').style.display = 'none';
    //document.getElementById('navCad li').style.display = 'block';
 });

 document.getElementById('id-comprarHTML').addEventListener('click', function() {
    // Seleciona os elementos para ocultar
    document.querySelector('.section-boasVindas').style.display = 'none';
    document.querySelector('.sobre').style.display = 'none';
    document.querySelector('.section-vagas').style.display = 'none';
    document.querySelector('.section-1').style.display = 'none';
    document.querySelector('.section-2').style.display = 'none';
    document.querySelector('.section-3').style.display = 'none';
    document.getElementById('depCliente').style.display = 'none';
    document.querySelector('.depoimento').style.display = 'none';
    
    window.scrollTo({
        top: 0,
        behavior: 'smooth' // para uma rolagem suave
    });

    // Exibe o iframe de cadastro
    document.getElementById('id-formCadastro').style.display = 'block';

    if (event.target.classList.contains('btnComprar')) {
        // Define o input "Comprar" como checked
        document.getElementById("id-cursoHtml").checked = true;
    }

});


document.getElementById('id-comprarCSS').addEventListener('click', function() {
    // Seleciona os elementos para ocultar
    document.querySelector('.section-boasVindas').style.display = 'none';
    document.querySelector('.sobre').style.display = 'none';
    document.querySelector('.section-vagas').style.display = 'none';
    document.querySelector('.section-1').style.display = 'none';
    document.querySelector('.section-2').style.display = 'none';
    document.querySelector('.section-3').style.display = 'none';
    document.getElementById('depCliente').style.display = 'none';
    document.querySelector('.depoimento').style.display = 'none';
    
    window.scrollTo({
        top: 0,
        behavior: 'smooth' // para uma rolagem suave
    });
    // Exibe o iframe de cadastro
    document.getElementById('id-formCadastro').style.display = 'block';
});

document.getElementById('id-comprarJS').addEventListener('click', function() {
    // Seleciona os elementos para ocultar
    document.querySelector('.section-boasVindas').style.display = 'none';
    document.querySelector('.sobre').style.display = 'none';
    document.querySelector('.section-vagas').style.display = 'none';
    document.querySelector('.section-1').style.display = 'none';
    document.querySelector('.section-2').style.display = 'none';
    document.querySelector('.section-3').style.display = 'none';
    document.getElementById('depCliente').style.display = 'none';
    document.querySelector('.depoimento').style.display = 'none';
    
    window.scrollTo({
        top: 0,
        behavior: 'smooth' // para uma rolagem suave
    });
    // Exibe o iframe de cadastro
    document.getElementById('id-formCadastro').style.display = 'block';
});