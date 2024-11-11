

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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



        <h2>Listar Usuário por ID</h2>
        <form action="${pageContext.request.contextPath}/admin" method="GET">
            <input type="hidden" name="metodo" value="buscar">
            <label for="id">ID do Usuário:</label>
            <input type="text" id="id" name="id_user" required>
            <input type="submit" value="Enviar">
        </form>
        <br>

        <h1>Detalhes do usuario</h1>


        <c:if test="${not empty usuario && not empty endereco}">
            <p>ID : ${usuario.id_user}</p>
            <p>Nome : ${usuario.nome}</p>
            <p>Sobrenome : ${usuario.sobrenome}</p>
            <p>Nome Social : ${usuario.nome_social}</p>
            <p>Data de Nascimento : ${usuario.data_nascimento}</p>
            <p>Email : ${usuario.email}</p>                   
            <p>Método de Pagamento : ${usuario.tipo_pagamento}</p>
            <h1>Endereço do usuario:</h1>
            <p>Rua: ${endereco.rua}</p>
            <p>Bairro : ${endereco.bairro}</p>
            <p>Numero : ${endereco.numero}</p>
            <p>CEP : ${endereco.cep}</p>
            <p>Estado : ${endereco.estado}</p>
            <p>Cidade : ${endereco.cidade}</p>
            <p>Telefone Celular : ${usuario.telefone_cel}</p> 
            <p>Telefone Residencial : ${usuario.telefone_res}</p>
            <p>Curso : ${usuario.curso}</p>
        </c:if>

        <c:if test="${empty usuario || empty endereco}">
            <p>Usuário não encontrado ou detalhes não disponíveis.</p>
        </c:if>








    </body>
</html>
