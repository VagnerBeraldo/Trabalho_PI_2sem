<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    // Invalida a sessão atual se existir
    if (session != null) {
        session.invalidate(); // Destroi a sessão
    }
%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../../css/login.css">
</head>
<body>
  <main>
    <div id="login-container">
      <h1>Login - Adm</h1>
      <form action="${pageContext.request.contextPath}/admin" method="POST">
        <input type="hidden" id="id" name="metodo" value="login">

        <label for="id-email">E-mail</label>
        <input type="email" name="adm_email" id="id-email" placeholder="Digite seu e-email" autocomplete="on" required maxlength="149">

        <label for="id-password">Senha</label>
        <input type="password" name="adm_senha" id="id-password" placeholder="Digite sua senha" required maxlength="49">

        <a href="#" id="recuperarSenha">Esqueceu a senha?</a>
        <input type="submit" value="Login">
      </form>
      <div id="rodapeLogin">
        <p>Seja Bem-Vindo ao 2MA Cursos</p>
        <a href="${pageContext.request.contextPath}/">Fechar</a>
      </div>
    </div>
</main>

</body>
</html>
