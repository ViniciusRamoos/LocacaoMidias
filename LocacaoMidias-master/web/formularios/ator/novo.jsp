<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Novo Ator</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Novo Ator</h1>

    <form method="post" action="${cp}/processaAtores">

      <input name="acao" type="hidden" value="inserir"/>

      <table>
        <tr>
          <td class="alinharDireita">Nome:</td>
          <td>
            <input name="nome"
                   type="text"
                   size="20"
                   maxlength="45"
                   required/>
          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Sobrenome:</td>
          <td>
            <input name="sobrenome"
                   type="text"
                   size="20"
                   maxlength="45"
                   required/>
          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Data de Estreia:</td>
          <td>
            <input name="dataEstreia"
                   type="date"
                   size="8"
                   placeholder="dd/mm/aaaa"
                   required/>
          </td>
        </tr>
      </table>

      <div class="botao-container">
        <a href="${cp}/formularios/ator/listagem.jsp">Voltar</a>
        <input type="submit" value="Salvar"/>
      </div>

    </form>
    </div>
  </body>

</html>
