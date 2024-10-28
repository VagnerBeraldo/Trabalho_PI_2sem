

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listagem de todos os usuarios do sistema</h1>
        
        <p>Aqui tera um formulario Listar com botao atualizar e Exlcuir igual a gente na sala</p>
        <p>Ele enviara uma requisição automatica para o controller e a funcao listar , que retornara todos os usuarios ,e dinaminacamente gerara uma lista com todos os users</p>
        <p>O botao excluir e o botao atualizar , pegara o ID de cada usuario de sua linha , e cada botao excluir e atulizar enviara para a rota listarUsers e atualizar User o ID do usuario</p>
        
        
        
        <h2>Excluir Usuário</h2>
        <form action="${pageContext.request.contextPath}/usuario" method="POST">
            <input type="hidden" name="metodo" value="deletar">
            <label for="id">ID do Usuário:</label>
            <input type="text" id="id" name="id_user" required>
            <input type="submit" value="Excluir">
        </form>
            br
                
            <h1>Detalhes do usuario</h1>
            
            <
            <c:if test="${not empty mensagem}}">
                <p>ID : ${usuario.getId_user()}</p>
                <p>Nome : ${usuario.getNome()}</p>
                <p>Sobrenome : ${usuario.getSobrenome()}</p>
                <p>Nome Social : ${usuario.getNome_social()}</p>
                <p>cpf : ${usuario.getCpf()}</p>
                <p>Data de Nascimento : ${usuario.getData_nascimento()}</p>
                <p>Email  : ${usuario.getEmail()}</p>
                <p>Metodo de Pagamento : ${usuarioget.Tipo_pagamento()}</p>
            </c:if>
        
            
            
            
            
    </body>
</html>
