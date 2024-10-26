
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String mensagemSucesso = request.getParameter("mensagemSucesso");
            if (mensagemSucesso != null) {
        %>
        <script>
             alert("<%= mensagemSucesso%>");
        </script>
        <%
            }
        %>




    </body>
</html>
