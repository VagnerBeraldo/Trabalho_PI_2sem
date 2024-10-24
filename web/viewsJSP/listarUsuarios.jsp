<%-- 
    Document   : listarUsuarios
    Created on : 24 de out. de 2024, 16:14:19
    Author     : erick
--%>

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
    </body>
</html>
