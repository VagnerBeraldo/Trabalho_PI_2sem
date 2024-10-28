document.addEventListener("DOMContentLoaded", function () {
    // Verifica se já houve interação com o pop-up nesta sessão
    if (!sessionStorage.getItem("popupStatus")) {
        document.querySelector(".poupap").style.display = "flex";
    } else {
        document.querySelector(".poupap").style.display = "none";
    }

    // Função para manipular o clique nos botões
    function handlePopupInteraction() {
        sessionStorage.setItem("popupStatus", "interacted");
        document.querySelector(".poupap").style.display = "none";
    }

    // Eventos para os botões "Concordar" e "Recusar"
    document.querySelector(".aceitar button").addEventListener("click", handlePopupInteraction);
    document.querySelector(".recusar button").addEventListener("click", handlePopupInteraction);
});