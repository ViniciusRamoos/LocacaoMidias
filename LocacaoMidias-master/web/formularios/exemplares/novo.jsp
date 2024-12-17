<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Nova Midia</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Nova Midia</h1>

    <form method="post" action="${cp}/processaExemplares">

      <input name="acao" type="hidden" value="inserir"/>

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
                <option value="${midia.id}">
                  ${midia.titulo}
                </option>
              </c:forEach>
            </select> 

          </td>
        </tr>
      </table>

    </form>
    <div class="botao-container">
            <a href="${cp}/formularios/exemplares/listagem.jsp"> Voltar </a>
            <input type="submit" value="Salvar"/>
    </div>
    </div>
  </body>

</html>
