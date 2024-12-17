<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Midia</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Excluir Midia</h1>

    <form method="post" action="${cp}/processaMidias">

      <input name="acao" type="hidden" value="excluir"/>
      <input name="id" type="hidden" value="${requestScope.midia.id}"/>

      <table>
        <tr>
          <td class="alinharDireita">Titulo:</td>
          <td>${requestScope.midia.titulo}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Ano de Lançamento:</td>
          <td>${requestScope.midia.anoLancamento}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Codigo de Barras:</td>
          <td>${requestScope.midia.codigoBarras}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Duração (Minutos):</td>
          <td>${requestScope.midia.duracaoMinutos}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Ator Principal:</td>
          <td>${requestScope.midia.atorPrincipal.nome} ${requestScope.midia.atorPrincipal.sobrenome}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Ator Coadjuvante:</td>
          <td>${requestScope.midia.atorCoadjuvante.nome} ${requestScope.midia.atorCoadjuvante.sobrenome}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Gênero:</td>
          <td>${requestScope.midia.genero.descricao}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Classificação Etária:</td>
          <td>${requestScope.midia.classificacaoEtaria.descricao}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Gênero:</td>
          <td>${requestScope.midia.tipo.descricao}</td>
        </tr>
        <tr>
          <td class="alinharDireita">Classificação Etária:</td>
          <td>${requestScope.midia.classificacaoInterna.descricao}</td>
        </tr>
      </table>
    </form> 
        <div class="botao-container">
          <a href="${cp}/formularios/midias/listagem.jsp"> Voltar </a>
            <input type="submit" value="Excluir"/>
        </div>
  </div>
  </body>

</html>
