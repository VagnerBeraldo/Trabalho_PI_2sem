<%@page import="model.Administrador"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   
    // Verifica se a sessão existe e se o usuário está logado
    Administrador administrador = (Administrador) session.getAttribute("usuarioLogado");

    if (administrador == null) {
        // Redireciona para a página de login se o usuário não estiver logado
        response.sendRedirect(request.getContextPath() + "/viewsJSP/admViews/loginAdm.jsp");
        return;
    }
%>
<!DOCTYPE html>

<html>

<head>
    <title></title>
    <meta charset="ISO-8859-1"> <!--ISO-8859-1-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/styleFormulario.css">
    <link rel="stylesheet" href="../../css/cadastrar.css">
</head>

<body>

    <h3>Consultar dados do Clientes</h3>
    <form class="form" action="${pageContext.request.contextPath}/admin" method="POST">
        <p>
             <input type="hidden" id="id" name="metodo" value="deletar">
            <label for="c">ID Cliente para Exclusão :</label>
            <input type="text" id="c" name="id_user" required>
            <input class="btn" type="submit" value="Buscar">
        </p>

    </form>
    
</body>

</html>