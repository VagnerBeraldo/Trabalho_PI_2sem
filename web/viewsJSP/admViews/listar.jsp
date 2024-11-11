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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Listar todos os</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
  </head>


  <body style="background-color:#E0E0E0;">

    <div class=".container-fluid my-3 ">

      <div class="container my-4 text-center">
          <form id="metodoForm" action="${pageContext.request.contextPath}/admin" method="GET">
              <input type="hidden" name="metodo" value="listar" />
              <button type="submit" class="btn btn-primary">Listar Todos os Usuários</button>
          </form>
      </div>

      <c:if test="${not empty usuarios}">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover w-100">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Sobrenome</th>
                            <th>Nome Social</th>
                            <th>Data de Nascimento</th>
                            <th>Email</th>
                            <th>Tipo de Pagamento</th>
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
                        <c:forEach var="usuarioEnderecoDTO" items="${usuarios}">
                            <tr>
                                <td>${usuarioEnderecoDTO.usuario.id_user}</td>
                                <td>${usuarioEnderecoDTO.usuario.nome}</td>
                                <td>${usuarioEnderecoDTO.usuario.sobrenome}</td>
                                <td>${usuarioEnderecoDTO.usuario.nome_social}</td>
                                <td>${usuarioEnderecoDTO.usuario.data_nascimento}</td>
                                <td>${usuarioEnderecoDTO.usuario.email}</td>
                                <td>${usuarioEnderecoDTO.usuario.tipo_pagamento}</td>
                                <td>${usuarioEnderecoDTO.endereco.rua}</td>
                                <td>${usuarioEnderecoDTO.endereco.bairro}</td>
                                <td>${usuarioEnderecoDTO.endereco.numero}</td>
                                <td>${usuarioEnderecoDTO.endereco.cep}</td>
                                <td>${usuarioEnderecoDTO.endereco.estado}</td>
                                <td>${usuarioEnderecoDTO.endereco.cidade}</td>
                                <td>${usuarioEnderecoDTO.usuario.telefone_cel}</td>
                                <td>${usuarioEnderecoDTO.usuario.telefone_res}</td>
                                <td>${usuarioEnderecoDTO.usuario.curso}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
              
      </c:if>

      <c:if test="${empty usuarios}">
        <div class="alert alert-warning">Nenhum usuário encontrado ou Listagem não requisitada.</div>
      </c:if>


     
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
