<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Alterar Exemplar</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Alterar Exemplar</h1>

    <form method="post" action="${cp}/processaExemplares">

      <input name="acao" type="hidden" value="alterar"/>
      <input name="codigoInterno" type="hidden" value="${requestScope.exemplar.codigoInterno}"/>

      <table>
        <tr>
          <td class="alinharDireita">Midia:</td>
          <td>

            <jsp:useBean 
                id="servicos" 
                scope="page" 
                class="locacaomidias.servicos.MidiaServices"/>

            <select name="idMidia" required>
              <c:forEach items="${servicos.todos}" var="midia">
                  <c:choose>
                      <c:when test="${requestScope.exemplar.midia.id eq midia.id}">
                          <option value="${midia.id}" selected>
                            ${midia.titulo}
                          </option>
                      </c:when>
                      <c:otherwise>
                          <option value="${midia.id}">
                            ${midia.titulo}
                          </option>
                      </c:otherwise>
                  </c:choose>
                
              </c:forEach>
            </select>

          </td>
        </tr>
      </table>
                    <div class="botao-container">
            <a href="${cp}/formularios/exemplares/listagem.jsp"> Voltar </a>
            <input type="submit" value="Salvar"/>
        </div> 
    </form>       
    </div>
  </body>

</html>
