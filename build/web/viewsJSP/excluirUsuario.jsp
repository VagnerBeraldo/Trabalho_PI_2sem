<!-- excluirUsuario.jsp -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de Exclusao por ID</title>
    </head>
    <body>



        <h2>Excluir Usuário</h2>
        <form action="${pageContext.request.contextPath}/usuario" method="POST">
            <input type="hidden" name="metodo" value="deletar">
            <label for="id">ID do Usuário:</label>
            <input type="text" id="id" name="id_user" required>
            <input type="submit" value="Excluir">
        </form>

        <%
            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null) {
        %>
        <p><%= mensagem%></p>
        <%
            }
        %>

    </body>
</html>
