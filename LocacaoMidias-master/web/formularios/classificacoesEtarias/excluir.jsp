<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Classificação Etária</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Excluir Classificação Etária</h1>

    <form method="post" action="${cp}/processaClassificacoesEtarias">

      <input name="acao" type="hidden" value="excluir"/>
      <input name="id" type="hidden" value="${requestScope.classificacaoEtaria.id}"/>

      <table>
        <tr>
          <td class="alinharDireita">Descrição:</td>
          <td>${requestScope.classificacaoEtaria.descricao}</td>
        </tr>
      </table>
        <div class="botao-container">
            <a href="${cp}/formularios/classificacoesEtarias/listagem.jsp"> Voltar </a>
            <input type="submit" value="Excluir"/>
        </div>
    </form>
    </div>
  </body>

</html>
