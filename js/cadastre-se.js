
document.getElementById('id-cadastrese').addEventListener('click', function() {
     // Seleciona os elementos para ocultar
     document.querySelector('.section-boasVindas').style.display = 'none';
     document.querySelector('.section-1').style.display = 'none';
     document.querySelector('.section-2').style.display = 'none';
     document.querySelector('.depoimentos').style.display = 'none';

     // Exibe o iframe de cadastro
     document.getElementById('id-formCadastro').style.display = 'block';


 });
