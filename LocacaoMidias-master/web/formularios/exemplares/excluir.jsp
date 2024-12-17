<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Exemplar</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Excluir Exemplar</h1>

    <form method="post" action="${cp}/processaExemplares">

      <input name="acao" type="hidden" value="excluir"/>
      <input name="codigoInterno" type="hidden" value="${requestScope.exemplar.codigoInterno}"/>

      <table>
        <tr>
          <td class="alinharDireita">Disponibilidade:</td>
          <c:choose>
              <c:when test="${exemplar.disponivel}">
                  <td>Disponível</td>
              </c:when>
              <c:otherwise>
                  <td>Não Disponível</td>
              </c:otherwise>
          </c:choose>
        </tr>
        <tr>
          <td class="alinharDireita">Midia:</td>
          <td>${requestScope.exemplar.midia.titulo}</td>
        </tr>        
      </table>
        <div class="botao-container">
            <a href="${cp}/formularios/exemplares/listagem.jsp"> Voltar </a>
            <input type="submit" value="Excluir"/> 
    </div>
    </form>
    </div>
  </body>

</html>
