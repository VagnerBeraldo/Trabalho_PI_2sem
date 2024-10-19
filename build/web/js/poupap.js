document.addEventListener('DOMContentLoaded', function() {
    // Seleciona os botões "Concordo" e "Recusar"
    const concordoButton = document.querySelector('.aceitar button');
    const recusarButton = document.querySelector('.recusar button');

    // Função para ocultar o popup
    function esconderPopup() {
        const popup = document.querySelector('.poupap');
        popup.style.display = 'none';
    }

    // Adiciona o evento de clique nos botões
    concordoButton.addEventListener('click', esconderPopup);
    recusarButton.addEventListener('click', esconderPopup);
});
