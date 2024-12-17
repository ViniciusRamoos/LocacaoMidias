<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaCidades?acao=preparar"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Cidades Cadastradas</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Cidades Cadastradas</h1>

    <table class="tabelaListagem">
      <thead>
        <tr>
          <th>Id</th>
          <th>Nome</th>
          <th>Estado</th>
          <th>Alterar</th>
          <th>Excluir</th>
        </tr>
      </thead>
      <tbody>

        <jsp:useBean
            id="servicos"
            scope="page"
            class="locacaomidias.servicos.CidadeServices"/>

        <c:forEach items="${servicos.todos}" var="cidade">
          <tr>
            <td>${cidade.id}</td>
            <td>${cidade.nome}</td>
            <td>${cidade.estado.sigla}</td>
            <td>
              <a href="${cp}/${prefixo}Alteracao&id=${cidade.id}">
                Alterar
              </a>
            </td>
            <td>
              <a href="${cp}/${prefixo}Exclusao&id=${cidade.id}">
                Excluir
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class="botao-container">
        <p>
          <a href="${cp}/formularios/cidades/novo.jsp">
            Nova Cidade
          </a>
        </p>
        <p><a href="${cp}/index.jsp">Tela Principal</a></p>
    </div>
    </div>
  </body>

</html>
