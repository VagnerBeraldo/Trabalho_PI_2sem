
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>

<head>
    <title></title>
    <meta charset="ISO-8859-1"> <!--ISO-8859-1-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/styleFormulario.css">
    <link rel="stylesheet" href="./css/cadastrar.css">
</head>

<body>
    <%
    String c;
    Connection conn;
    PreparedStatement stm;
    ResultSet rs;


    c = request.getParameter("codigo");


    /*CONECTAR COM BANCO DE DADOS*/
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NOME-BANCO-DADOS", "root", "SENHA);
    /* Busca o usuário e senha digitados na tabela do BD*/
    stm = conn.prepareStatement("SELECT * FROM NOME-TABELA WHERE id = ?");
    stm.setString(1, c);

    rs = stm.executeQuery();
    if (rs.next()) {
%>    
    <h3>Alterar dados do Clientes</h3>
    <form class="form" method="post" action="alterarCadastro.jsp">
        <fieldset class="infoPessoal">
            <legend>Dados Pessoais</legend>
            <div class="codCliente">
                <label for="id-codCliente">Cod Cliente:</label>                    <!-- no lugar do XXXXX nome igual ao banco de dados -->
                <input type="text" id="id-codCliente" maxlength="15" required value="<%=rs.getString("XXXXX")%>" readonly>
            </div>
            <div class="nomeCliente">
                <label for="id-nomeCliente">Primeiro Nome*</label>
                <input type="text" id="id-nomeCliente" maxlength="30" required value="<%=rs.getString("XXXXX")%>">
            </div>
             <div class="sobreNomeCliente">
                <label for="id-sobreNomeCliente">Sobrenome*</label>
                <input type="text" id="id-sobreNomeCliente" maxlength="60" required value="<%=rs.getString("XXXXX")%>">
             </div>
            <div class="nomeSocial"> 
                <label for="id-nomeSocial">Nome Social</label>
                <input type="text" maxlength="30" id="id-nomeSocial"value="<%=rs.getString("XXXXX")%>">
            </div>
            <div class="cpf"> 
                <label for="id-cpf">CPF*</label>
                <input type="text" id="id-cpf" maxlength="11" required value="<%=rs.getString("XXXXX")%>">
             </div>
            <div class="dtNascimento">
                <label for="id-dtNasc">Data Nasc.*</label>
                <input type="date" maxlength="8" id="id-dtNasc" required value="<%=rs.getString("XXXXX")%>">
            </div>
        </fieldset>

        <br>

        <fieldset class="infoContato">
            <legend>Dados de Contato</legend>
            <div class="email">
                <label for="id-email">e-mail*</label>
                <input type="email" name="email" maxlength="60" id="id-email" required value="<%=rs.getString("XXXXX")%>">
            </div>
            <div class="telefoneCelular">
                <label for="id-telefoneCelular">Tel. Celular</label>
                <input type="tel" name="telefoneCelular" maxlength="11" id="id-telefoneCelular" value="<%=rs.getString("XXXXX")%>">
            </div>
            <div class="telefoneResid">
                <label for="id-telefoneResid">Tel. Residencial</label>
                <input type="tel" name="telefoneResid" maxlength="10" id="id-telefoneResid" value="<%=rs.getString("XXXXX")%>">
            </div>
        </fieldset>

        <br>

        <fieldset class="endereco">
            <legend>Logradouro</legend>
            <div class="cep">
                <label for="id-cep">CEP*</label>
                <input type="text" name="cep" id="id-cep" maxlength="8" required onblur="buscarEndereco()" value="<%=rs.getString("XXXXX")%>">
            </div>
            <div class="rua">
                <label for="id-rua">Rua*</label>
                <input type="text" name="rua" maxlength="80" id="id-rua" required value="<%=rs.getString("XXXXX")%>">
            </div>
            <div class="numero">
                <label for="id-numero">Numeral*</label>
                <input type="text" name="numero" maxlength="6" id="id-numero" required value="<%=rs.getString("XXXXX")%>">
            </div>
            <div class="complemento">
                <label for="id-complemento">Complemento</label>
                <input type="text" name="complemento" maxlength="30" id="id-complemento" value="<%=rs.getString("XXXXX")%>">
            </div>
            <div class="bairro">
                <label for="id-bairro">Bairro*</label>
                <input type="text" name="bairro" maxlength="30" id="id-bairro" required value="<%=rs.getString("XXXXX")%>">
            </div>
            <div class="cidade">
                <label for="id-cidade">Cidade*</label>
                <input type="text" name="cidade" maxlength="20" id="id-cidade" required value="<%=rs.getString("XXXXX")%>">
            </div>

            <div class="estado">
                <label for="id-estado">Estado*</label>
                <select name="estado" id="id-estado" required value="<%=rs.getString("XXXXX")%>">
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
            <input type="checkbox" name="curso" id="id-cursoHtml">
            <label for="id-cursoHtml">Html</label>

            <input type="checkbox" name="curso" id="id-cursoCSS">
            <label for="id-cursoCSSI">CSS</label>

            <input type="checkbox" name="curso" id="id-cursoJS">
            <label for="id-cursoJSI">Javascript</label>
        </fieldset>

        <br>

        <fieldset class="pagamento">
            <legend>Forma de Pagamento</legend>
            <input type="radio" name="pagamento" id="id-pagamentoCD">
            <label for="id-pagamentoCD">Cartão de Débito</label>

            <input type="radio" name="pagamento" id="id-pagamentoCC">
            <label for="id-pagamentoCC">Cartão de Crédito</label>

            <input type="radio" name="pagamento" id="id-pagamentoPIX">
            <label for="id-pagamentoPIX">PIX</label>

            <input type="radio" name="pagamento" id="id-pagamentoBC">
            <label for="id-pagamentoBC">Boleto Bancário</label>
        </fieldset>
        <p>  
            <input class="btn" type="submit" value="Salvar Alteração">
        </p>
    </form>
    <p><br>
        * Preenchimento Obrigatório
    </p>
</body>

</html>