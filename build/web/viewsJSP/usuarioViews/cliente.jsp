<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
   
    // Verifica se a sessão existe e se o usuário está logado
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    if (usuario == null) {
        // Redireciona para a página de login se o usuário não estiver logado
        response.sendRedirect(request.getContextPath() + "/viewsJSP/usuarioViews/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Cursos de HTML, CSS e Javascript">
    <meta name="keywords" content="HTML, CSS, JS, Javascript">
    <meta name="robots" content="index, Follow">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
        rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Viga&display=swap"
        rel="stylesheet">

    <link rel="stylesheet" href="../../css/index.css">
    <link rel="stylesheet" href="../../css/adm.css">



    <title>Pagina do Cliente</title>

</head>

<body>
    <header class="header">
        <div class="logo">
            <img src="./img/logoPW.png" id="logo" alt="">
        </div>

        <div class="tituloPagina">
            <h3>2MA Painel Cliente</h3>
        </div>

        <div class="redeSocial">

            <div class="rede">
                <a href="https://www.facebook.com/universitariosantoamaro" target="_blank"><img src="./img/facebook.png"
                        alt="icone da rede social facebook"></a>
            </div>
            <div class="rede">
                <a href="https://www.instagram.com/senacsantoamaro/" target="_blank"><img src="./img/instagram.png"
                        alt="ícone da rede social instagram"></a>
            </div>
            <div class="rede">
                <a href="https://t.me/senacsantoamaro/" target="_blank"><img src="./img/telegram.png" id="imgTeleg"
                        alt="ícone da rede social telegram"></a>
            </div>
            <div class="rede">
                <a href="https://wa.me/5511996616224" target="_blank"><img src="./img/whatsapp.png"
                        alt="ícone da rede social whatsapp"></a>
            </div>
        </div>
        <div class="navigation">
            <nav id="nav">
                <button aria-label="Abrir Menu" id="btn-mobile" aria-haspopup="true" aria-controls="menu"
                    aria-expanded="false">
                    <span id="hamburger"></span>
                </button>
                <ul id="menu" role="menu">
                    
                    <li><a href="/Trabalho_PI_2MA/index.html" >Página Principal</a></li>
                    <li><a href="${pageContext.request.contextPath}/viewsJSP/usuarioViews/login.jsp" >Sair do Sistema</a></li>
                    
                </ul>
                
            </nav>

        </div>

    </header>
    <main>
        <div style="margin-top: 60px;">
            <hr>        
            </div>
            
        <div class="tituloPagina" >
            <h3 style="position: relative; margin-top: 100px; right: 8%; ">Àrea Exclusiva do cliente</h3>
        </div>
      

        <footer style="position: fixed; margin-bottom: 0;">
            <div class="infoAdicionalRodape">
                <a class="footerLocalizacao"
                    href="https://www.google.com.br/maps/place/Centro+Universit%C3%A1rio+Senac+-+Santo+Amaro/@-23.6695931,-46.7020657,17z/data=!3m1!4b1!4m6!3m5!1s0x94ce5036539648d5:0x78501a72680ea23a!8m2!3d-23.669598!4d-46.6994908!16s%2Fg%2F121jytr5?hl=pt-BR&entry=ttu"
                    target="_blank">Acesse nossa Localização</a>
            </div>
            <div class="senac">
                <address>

                    <p>&copy 2MA Cursos - 2024. Todos os direitos reservados.<br></p>
                    <p>Senac Santo Amaro</p>
                    <p>Av. Eng. Eusébio Stevaux, 823 - Santo Amaro, São Paulo - SP, 04696-000</p>

                </address>
            </div>
        </footer>

</body>

</html>
