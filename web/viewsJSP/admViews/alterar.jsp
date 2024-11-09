<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleFormulario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastrar.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formCadastro.css">

    </head>
    <body>
        <h3>Alterar dados do Clientes</h3>

        <form class="form" action="${pageContext.request.contextPath}/admin" method="GET" >
            <input type="hidden" id="id" name="metodo" value="atualizar">
            <p>  
                <label for="c">ID Cliente:</label>
                <input id="c" type="text" name="id_user" size="15">
                <input class="btn" type="submit" value="Enviar">
            </p>
        </form>

        <c:if test="${not empty usuario && not empty endereco}">

            <section class="cadastro">

                <div class="cadastro_cabecalho">
                    <div class="nomeEmpresa">
                        <h3><b><ins>2MA - Cursos On-line</ins></b></h3>
                    </div>
                </div>



                <form action="${pageContext.request.contextPath}/admin" method="POST">
                    <input type="hidden" id="id" name="metodo" value="atualizar">
                    <fieldset class="infoPessoal">
                        <legend>Informações Pessoais</legend>
                        <div class="nomeCliente">

                            <label for="id-nomeCliente">Primeiro Nome*</label>
                            <input type="text" name="user_nome" id="id-nomeCliente" maxlength="30" require value="${usuario.nome}">
                        </div>
                        <div class="sobrenomeCliente">
                            <label for="id-sobrenomeCliente">Sobrenome*</label>
                            <input type="text" name="user_sobrenome" id="id-sobrenomeCliente" maxlength="60" required value="${usuario.sobrenome}">
                        </div>
                        <div class="nomeSocial">
                            <label for="id-nomeSocial">Nome Social</label>
                            <input type="text" name="user_nomeSocial" maxlength="30" id="id-nomeSocial" value="${usuario.nome_social}">
                        </div>
                        <div class="cpf">
                            <label for="id-cpf">CPF*</label>
                            <input type="text" name="user_cpf" id="id-cpf" maxlength="11" required value="${usuario.cpf}" readonly>
                        </div>
                        <div class="dtNascimento">
                            <label for="id-dtNasc">Data Nasc.*</label>
                            <input type="date" name="user_nascimento" maxlength="8" id="id-dtNasc" required value="${usuario.data_nascimento}" readonly>
                        </div>
                   
                        <div class="idCliente">
                            <label for="idCliente">ID-Usuario : * </label>
                            <input type="text" name="id_user" maxlength="100" id="idCliente" required value="${usuario.id_user}" readonly> 
                        </div>


                    </fieldset>

                    <br>

                    <fieldset class="infoContado">
                        <legend>Informações de Contato</legend>
                        <div class="email">
                            <label for="id-email">e-mail*</label>
                            <input type="email" name="user_email" maxlength="60" id="id-email" required value="${usuario.email}"> 
                        </div>
                        <div class="telefoneCelular">
                            <label for="id-telefoneCelular">nº Tel. Celular</label>
                            <input type="tel" name="user_telefoneCelular" maxlength="11" id="id-telefoneCelular" value="${usuario.telefone_cel}">
                        </div>
                        <div class="telefoneResid">
                            <label for="id-telefoneResid">nº Tel. Residencial</label>
                            <input type="tel" name="user_telefoneResid" maxlength="10" id="id-telefoneResid" value="${usuario.telefone_res}">
                        </div>
                    </fieldset>

                    <br>

                    <fieldset class="endereco">
                        <legend>Endereço</legend>
                        <div class="cep">
                            <label for="id-cep">CEP*</label>
                            <input type="text" name="user_ender_cep" id="id-cep" maxlength="8" required value="${endereco.cep}">
                        </div>
                        <div class="rua">
                            <label for="id-rua">Rua*</label>
                            <input type="text" name="user_ender_rua" maxlength="80" id="id-rua" required value="${endereco.rua}">
                        </div>
                        <div class="numero">
                            <label for="id-numero">Nº*</label>
                            <input type="text" name="user_ender_numero" maxlength="6" id="id-numero" required value="${endereco.numero}">
                        </div>
                        <div class="complemento">
                            <label for="id-complemento">Complemento</label>
                            <input type="text" name="user_ender_complemento" maxlength="30" id="id-complemento" value="${endereco.complemento}">
                        </div>
                        <div class="bairro">
                            <label for="id-bairro">Bairro*</label>
                            <input type="text" name="user_ender_bairro" maxlength="30" id="id-bairro" required value="${endereco.bairro}">
                        </div>
                        <div class="cidade">
                            <label for="id-cidade">Cidade*</label>
                            <input type="text" name="user_ender_cidade" maxlength="20" id="id-cidade" required value="${endereco.cidade}">
                        </div>

                        <div class="estado">
                            <label for="id-estado">Estado*</label>
                            <select name="user_ender_estado" id="id-estado" required>
                                <c:forEach var="estado" items="${endereco.estado}">
                                    <option value="${estado}" ${estado == endereco.estado ? 'selected' : ''}>${estado}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </fieldset>

                    <br>

                    <fieldset class="preferencias">
                        <legend>Cursos de Interesse</legend>

                               <input type="radio" name="curso_user" id="id-cursoHtml" value="HTML" 
                               <c:if test="${fn:contains(usuario.curso, 'HTML')}">checked</c:if> >
                               <label for="id-cursoHtml">Html</label>

                               <input type="radio" name="curso_user" id="id-cursoCSS" value="CSS" 
                               <c:if test="${fn:contains(usuario.curso, 'CSS')}">checked</c:if> >
                               <label for="id-cursoCSSI">CSS</label>

                               <input type="radio" name="curso_user" id="id-cursoJS" value="JAVASCRIPT" 
                               <c:if test="${fn:contains(usuario.curso, 'JAVASCRIPT')}">checked</c:if> >
                               <label for="id-cursoJSI">JavaScript</label> 

                               <input type="radio" name="curso_user" id="id-cursoNODEJS" value="NODEJS" 
                               <c:if test="${fn:contains(usuario.curso, 'NODEJS')}">checked</c:if> >
                               <label for="id-cursoHtml">Node JS</label>

                               <input type="radio" name="curso_user" id="id-cursoJAVA" value="JAVA" 
                               <c:if test="${fn:contains(usuario.curso, 'JAVA')}">checked</c:if> >
                               <label for="id-cursoCSSI">Java WEB</label>

                               <input type="radio" name="curso_user" id="id-cursoPHP" value="PHP" 
                               <c:if test="${fn:contains(usuario.curso, 'PHP')}">checked</c:if> >
                               <label for="id-cursoJSI">PHP 8.0+</label> 

                        </fieldset>

                        <br>

                        <fieldset class="pagamento">
                            <legend>Forma de Pagamento</legend>
                            <input type="radio" name="user_pagamento" id="id-pagamentoCD" value="debito"
                            <c:if test="${usuario.tipo_pagamento == 'debito'}">checked</c:if> >
                            <label for="id-pagamentoCD">Cartão de Débito</label>

                            <input type="radio" name="user_pagamento" id="id-pagamentoCC" value="credito"
                            <c:if test="${usuario.tipo_pagamento == 'credito'}">checked</c:if> >
                            <label for="id-pagamentoCC">Cartão de Crédito</label>

                            <input type="radio" name="user_pagamento" id="id-pagamentoPIX" value="pix"
                            <c:if test="${usuario.tipo_pagamento == 'pix'}">checked</c:if> >
                            <label for="id-pagamentoPIX">PIX</label>

                            <input type="radio" name="user_pagamento" id="id-pagamentoBC" value="boleto"
                            <c:if test="${usuario.tipo_pagamento == 'boleto'}">checked</c:if> >
                            <label for="id-pagamentoBC">Boleto Bancário</label>
                        </fieldset>


                        <div class="termo">
                            <input type="checkbox" name="termo" id="idTermo" required>
                            <label for="idTermo">Declaro que li e concorno com a <a href="#">política de privacidade.</a></label>
                        </div>
                        <div class="botoes">
                            <button type="submit" id="enviar">Enviar</button>
                        </div>
                    </form>

                </section>
        </c:if>

        <c:if test="${empty usuario || empty endereco}">
            <div class="alert alert-warning">Usuário não encontrado ou Consulta não Requisitada.</div>
        </c:if>






        <!-- <c:if test="${not empty usuario && not empty endereco}">
            <table class="table table-striped table-bordered table-hover w-100">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Sobrenome</th>
                        <th>Nome Social</th>
                        <th>Data de Nascimento</th>
                        <th>Senha</th>
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
                        <td>${usuario.senha}</td>
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
        </c:if> -->
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <script src="../../js/api-ViaCep.js"></script>

</body>
</html>
