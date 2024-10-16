// Aguarda o carregamento completo do DOM
document.addEventListener('DOMContentLoaded', function() {
    var imgBusca = document.getElementById('imgBusca');
    var divBuscador = document.getElementById('divBuscador');
    var fecharBusca = document.getElementById('fecharBusca');

    // Evento para abrir o buscador
    imgBusca.addEventListener('click', function() {
        divBuscador.classList.add('visivel');
    });

    // Evento para fechar o buscador
    fecharBusca.addEventListener('click', function() {
        divBuscador.classList.remove('visivel');
    });
});
