<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Cursos de HTML, CSS e Javascript">
    <meta name="keywords" content="HTML, CSS, JS, Javascript">
    <meta name="robots" content="index, Follow">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
        rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Viga&display=swap"
        rel="stylesheet">


    <link rel="stylesheet" href="../../css/formCadastro.css">
</head>

<body>

    <section class="cadastro">

        <div class="cadastro_cabecalho">
            <div class="nomeEmpresa">
                <h3><b><ins>2MA - Cursos On-line</ins></b></h3>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/admin" method="POST">
            <input type="hidden" id="id" name="metodo" value="cadastrar">
            <fieldset class="infoPessoal">
                <legend>Informações Pessoais</legend>
                <div class="nomeCliente">
                    
                    <label for="id-nomeCliente">Primeiro Nome*</label>
                    <input type="text" name="user_nome" id="id-nomeCliente" maxlength="30" required placeholder="Ex.: João">
                </div>
                <div class="sobrenomeCliente">
                    <label for="id-sobrenomeCliente">Sobrenome*</label>
                    <input type="text" name="user_sobrenome" id="id-sobrenomeCliente" maxlength="60" required placeholder="da Silva Souza">
                </div>
                <div class="nomeSocial">
                    <label for="id-nomeSocial">Nome Social</label>
                    <input type="text" name="user_nomeSocial" maxlength="30" id="id-nomeSocial">
                </div>
                <div class="cpf">
                    <label for="id-cpf">CPF*</label>
                    <input type="text" name="user_cpf" id="id-cpf" maxlength="11" required placeholder="somente números">
                </div>
                <div class="dtNascimento">
                    <label for="id-dtNasc">Data Nasc.*</label>
                    <input type="date" name="user_nascimento" maxlength="8" id="id-dtNasc" required>
                </div>
                <div class="senhaCliente">
                    <label for="senhaCliente">Senha* </label>
                    <input type="password" name="user_senha" maxlength="100" id="senhaCliente" required>
                </div>
                
                
            </fieldset>

            <br>

            <fieldset class="infoContado">
                <legend>Informações de Contato</legend>
                <div class="email">
                    <label for="id-email">e-mail*</label>
                    <input type="email" name="user_email" maxlength="60" id="id-email" required>
                </div>
                <div class="telefoneCelular">
                    <label for="id-telefoneCelular">nº Tel. Celular</label>
                    <input type="tel" name="user_telefoneCelular" maxlength="11" id="id-telefoneCelular"
                        placeholder="somente números com DDD">
                </div>
                <div class="telefoneResid">
                    <label for="id-telefoneResid">nº Tel. Residencial</label>
                    <input type="tel" name="user_telefoneResid" maxlength="10" id="id-telefoneResid"
                        placeholder="somente números com DDD">
                </div>
            </fieldset>

            <br>

            <fieldset class="endereco">
                <legend>Endereço</legend>
                <div class="cep">
                    <label for="id-cep">CEP*</label>
                    <input type="text" name="user_ender_cep" id="id-cep" maxlength="8" required placeholder="somente números" onblur="buscarEndereco()">
                </div>
                <div class="rua">
                    <label for="id-rua">Rua*</label>
                    <input type="text" name="user_ender_rua" maxlength="80" id="id-rua" required>
                </div>
                <div class="numero">
                    <label for="id-numero">Nº*</label>
                    <input type="text" name="user_ender_numero" maxlength="6" id="id-numero" required>
                </div>
                <div class="complemento">
                    <label for="id-complemento">Complemento</label>
                    <input type="text" name="user_ender_complemento" maxlength="30" id="id-complemento">
                </div>
                <div class="bairro">
                    <label for="id-bairro">Bairro*</label>
                    <input type="text" name="user_ender_bairro" maxlength="30" id="id-bairro" required>
                </div>
                <div class="cidade">
                    <label for="id-cidade">Cidade*</label>
                    <input type="text" name="user_ender_cidade" maxlength="20" id="id-cidade" required>
                </div>
                
                <div class="estado">
                    <label for="id-estado">Estado*</label>
                    <select name="user_ender_estado" id="id-estado" required>
                        <option value="">Selecione o estado</option>
                        <option value="AC">Acre</option>
                        <option value="AL">Alagoas</option>
                        <option value="AP">Amapá</option>
                        <option value="AM">Amazonas</option>
                        <option value="BA">Bahia</option>
                        <option value="CE">Ceará</option>
                        <option value="DF">Distrito Federal</option>
                        <option value="ES">Espírito Santo</option>
                        <option value="GO">Goiás</option>
                        <option value="MA">Maranhão</option>
                        <option value="MT">Mato Grosso</option>
                        <option value="MS">Mato Grosso do Sul</option>
                        <option value="MG">Minas Gerais</option>
                        <option value="PA">Pará</option>
                        <option value="PB">Paraíba</option>
                        <option value="PR">Paraná</option>
                        <option value="PE">Pernambuco</option>
                        <option value="PI">Piauí</option>
                        <option value="RJ">Rio de Janeiro</option>
                        <option value="RN">Rio Grande do Norte</option>
                        <option value="RS">Rio Grande do Sul</option>
                        <option value="RO">Rondônia</option>
                        <option value="RR">Roraima</option>
                        <option value="SC">Santa Catarina</option>
                        <option value="SP">São Paulo</option>
                        <option value="SE">Sergipe</option>
                        <option value="TO">Tocantins</option>
                    </select>
                </div>
                
            </fieldset>

            <br>

            <fieldset class="preferencias">
                <legend>Cursos de Interesse</legend>
                <input type="checkbox" name="curso_user" id="id-cursoHtml" value="HTML">
                <label for="id-cursoHtml">Html</label>

                <input type="checkbox" name="curso_user" id="id-cursoCSS" value="CSS">
                <label for="id-cursoCSSI">CSS</label>

                <input type="checkbox" name="curso_user" id="id-cursoJS" value="JAVASCRIPT">
                <label for="id-cursoJSI">Javascript</label> 
            </fieldset>

            <br>

            <fieldset class="pagamento">
                <legend>Forma de Pagamento</legend>
                <input type="radio" name="user_pagamento" id="id-pagamentoCD" value="debito" >
                <label for="id-pagamentoCD">Cartão de Débito</label>

                <input type="radio" name="user_pagamento" id="id-pagamentoCC" value="credito">
                <label for="id-pagamentoCC">Cartão de Crédito</label>

                <input type="radio" name="user_pagamento" id="id-pagamentoPIX" value="pix">
                <label for="id-pagamentoPIX">PIX</label>

                <input type="radio" name="user_pagamento" id="id-pagamentoBC" value="boleto">
                <label for="id-pagamentoBC">Boleto Bancário</label>
            </fieldset>


            <div class="termo">
                <input type="checkbox" name="termo" id="idTermo" required>
                <label for="idTermo">Declaro que li e concorno com a <a href="#">política de privacidade.</a></label>
            </div>
            <div class="botoes">
                <button type="submit" id="enviar">Enviar</button>
                <button type="submit" id="alterar">Alterar</button>
                <button type="submit" id="consultar">Consultar</button>
                <button type="submit" id="excluir">Excluir</button>
            </div>
        </form>

    </section>

</body>
<script src="../../js/api-ViaCep.js"></script>

</html>