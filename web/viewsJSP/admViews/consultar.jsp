<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
    <title></title>
    <meta charset="ISO-8859-1"> <!--ISO-8859-1-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>

<body style="background-color:#E0E0E0;">
    <div class=".container-fluid my-3 ">

    <h3 style="text-align: center;">Consultar dados do Clientes via ID</h3>
    <br>
    <form action="${pageContext.request.contextPath}/admin" method="GET" class="text-center">
        <input type="hidden" name="metodo" value="buscar">
        <div class="mb-3 d-flex justify-content-center align-items-center">
            <label for="id-user" class="me-2"><h3>ID do Cliente : </h3></label>
            <input id="id-user" type="text" name="id_user" class="form-control me-2" placeholder="ID do Cliente" required style="width: 250px;">
            <button type="submit" class="btn btn-primary">Enviar</button>
        </div>
    </form>


    <br>


    <c:if test="${not empty usuario && not empty endereco}">
        <table class="table table-striped table-bordered table-hover w-100">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Sobrenome</th>
                    <th>Nome Social</th>
                    <th>Data de Nascimento</th>
                    <th>Email</th>
                    <th>Método de Pagamento</th>
                    <th>Rua</th>
                    <th>Bairro</th>
                    <th>Número</th>
                    <th>CEP</th>
                    <th>Estado</th>
                    <th>Cidade</th>
                    <th>Telefone Celular</th>
                    <th>Telefone Residencial</th>
                    <th>Curso</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${usuario.id_user}</td>
                    <td>${usuario.nome}</td>
                    <td>${usuario.sobrenome}</td>
                    <td>${usuario.nome_social}</td>
                    <td>${usuario.data_nascimento}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.tipo_pagamento}</td>
                    <td>${endereco.rua}</td>
                    <td>${endereco.bairro}</td>
                    <td>${endereco.numero}</td>
                    <td>${endereco.cep}</td>
                    <td>${endereco.estado}</td>
                    <td>${endereco.cidade}</td>
                    <td>${usuario.telefone_cel}</td>
                    <td>${usuario.telefone_res}</td>
                    <td>${usuario.curso}</td>
                </tr>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty usuario || empty endereco}">
        <div class="alert alert-warning">Usuário não encontrado ou Consulta não Requisitada.</div>
    </c:if>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
