<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Ator</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Excluir Ator</h1>

    <form method="post" action="${cp}/processaAtores">

      <input name="acao" type="hidden" value="excluir"/>
      <input name="id" type="hidden" value="${requestScope.ator.id}"/>

      <table>
        <tr>
          <td class="alinharDireita">Nome:</td>
          <td>${requestScope.ator.nome}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Sobrenome:</td>
          <td>${requestScope.ator.sobrenome}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Data de Estreia:</td>
          <td>
            <fmt:formatDate 
                pattern="dd/MM/yyyy"
                value="${requestScope.ator.dataEstreia}"/>
          </td>
        </tr>
      </table>
      <div class="botao-container">
            <a href="${cp}/formularios/ator/listagem.jsp"> Voltar </a>
            <input type="submit" value="Excluir"/>
      </div>
    </form>
    </div>
  </body>

</html>
