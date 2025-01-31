<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Alterar Tipo</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Alterar Tipo</h1>

    <form method="post" action="${cp}/processaTipos">

      <input name="acao" type="hidden" value="alterar"/>
      <input name="id" type="hidden" value="${requestScope.tipo.id}"/>

      <table>
        <tr>
          <td class="alinharDireita">Descrição:</td>
          <td>
            <input name="descricao"
                   type="text"
                   size="20"
                   maxlength="45"
                   value="${requestScope.tipo.descricao}"/>
          </td>
        </tr>
      </table> 
        <div class="botao-container">
            <a href="${cp}/formularios/tipos/listagem.jsp"> Voltar </a>
            <input type="submit" value="Alterar"/>
        </div>
    </form>
  </div>
  </body>
</html>
