<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaExemplares?acao=preparar"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Exemplares Cadastrados</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Exemplares Cadastrados</h1>


    <table class="tabelaListagem">
      <thead>
        <tr>
          <th>Codigo Interno</th>
          <th>Titulo</th>
          <th>Disponivel</th>
          <th>Alterar</th>
          <th>Excluir</th>
        </tr>
      </thead>
      <tbody>

        <jsp:useBean 
            id="servicos"
            scope="page"
            class="locacaomidias.servicos.ExemplarServices"/>

        <c:forEach items="${servicos.todos}" var="exemplar">
          <tr>
            <td>${exemplar.codigoInterno}</td>
            <td>${exemplar.midia.titulo}</td>
            <c:choose>
                <c:when test="${exemplar.disponivel}">
                    <td>Disponível</td>
                </c:when>
                <c:otherwise>
                    <td>Não Disponível</td>
                </c:otherwise>
            </c:choose>
            <td>
              <a href="${cp}/${prefixo}Alteracao&codigoInterno=${exemplar.codigoInterno}">
                Alterar
              </a>
            </td>
            <td>
              <a href="${cp}/${prefixo}Exclusao&codigoInterno=${exemplar.codigoInterno}">
                Excluir
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>

    </table>
    <div class="botao-container">
        <p>
          <a href="${cp}/formularios/exemplares/novo.jsp">
            Novo Exemplar
          </a>
        </p>

        <p><a href="${cp}/index.jsp">Tela Principal</a></p>
    </div>
    </div>
  </body>

</html>
