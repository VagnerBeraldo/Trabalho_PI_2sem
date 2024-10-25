document.getElementById('id-login').addEventListener('click', function() {
    const perfilSelecionado = document.querySelector('input[name="perfil"]:checked').value;

    if (perfilSelecionado === 'cliente') {
        window.location.href = 'login.html';
    } else if (perfilSelecionado === 'adm') {
        window.location.href = 'loginAdm.html';
    }
});