document.getElementById('id-login').addEventListener('click', function() {
    const perfilSelecionado = document.querySelector('input[name="perfil"]:checked').value;

    if (perfilSelecionado === 'cliente') {
        window.location.href = '/Trabalho_PI_2MA/viewsJSP/usuarioViews/login.jsp';
    } else if (perfilSelecionado === 'adm') {
        window.location.href = '/Trabalho_PI_2MA/viewsJSP/admViews/loginAdm.jsp';
    }
});