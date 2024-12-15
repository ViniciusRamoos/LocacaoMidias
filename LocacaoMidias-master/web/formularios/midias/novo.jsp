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

    <h1>Nova Midia</h1>

    <form method="post" action="${cp}/processaMidias">

      <input name="acao" type="hidden" value="inserir"/>

      <table>
        <tr>
          <td class="alinharDireita">Titulo:</td>
          <td>
            <input name="titulo"
                   type="text"
                   size="20"
                   maxlength="100"
                   required/>
          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Ano de Lançamento:</td>
          <td>
            <input name="anoLancamento"
                   type="text"
                   size="20"
                   maxlength="100"
                   required/>
          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Codigo de Barras:</td>
          <td>
            <input name="codigoBarras"
                   type="text"
                   size="20"
                   maxlength="13"
                   required/>
          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Duração (Minutos):</td>
          <td>
            <input name="duracaoEmMinutos"
                   type="number"
                   size="8"
                   step="1"
                   min="1"
                   required/>
          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Ator Principal:</td>
          <td>

            <jsp:useBean 
                id="servicosAtor" 
                scope="page" 
                class="locacaomidias.servicos.AtorServices"/>

            <select name="idAtorPrincipal" required>
              <c:forEach items="${servicosAtor.todos}" var="atorPrincipal">
                <option value="${atorPrincipal.id}">
                  ${atorPrincipal.nome} ${atorPrincipal.sobrenome}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Ator Coadjuvante:</td>
          <td>
            <select name="idAtorCoadjuvante" required>
              <c:forEach items="${servicosAtor.todos}" var="atorCoadjuvante">
                <option value="${atorCoadjuvante.id}">
                  ${atorCoadjuvante.nome} ${atorCoadjuvante.sobrenome}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Gênero:</td>
          <td>

            <jsp:useBean 
                id="servicosGenero" 
                scope="page" 
                class="locacaomidias.servicos.GeneroServices"/>

            <select name="idGenero" required>
              <c:forEach items="${servicosGenero.todos}" var="genero">
                <option value="${genero.id}">
                  ${genero.descricao}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Classificação Etária:</td>
          <td>

            <jsp:useBean 
                id="servicosCE" 
                scope="page" 
                class="locacaomidias.servicos.ClassificacaoEtariaServices"/>

            <select name="idClassificacaoEtaria" required>
              <c:forEach items="${servicosCE.todos}" var="CE">
                <option value="${CE.id}">
                  ${CE.descricao}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Tipo:</td>
          <td>

            <jsp:useBean 
                id="servicosTipo" 
                scope="page" 
                class="locacaomidias.servicos.TipoServices"/>

            <select name="idTipo" required>
              <c:forEach items="${servicosTipo.todos}" var="tipo">
                <option value="${tipo.id}">
                  ${tipo.descricao}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Classificação Interna:</td>
          <td>

            <jsp:useBean 
                id="servicosCI" 
                scope="page" 
                class="locacaomidias.servicos.ClassificacaoInternaServices"/>

            <select name="idClassificacaoInterna" required>
              <c:forEach items="${servicosCI.todos}" var="CI">
                <option value="${CI.id}">
                  ${CI.descricao}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>
        <tr>
          <td>
            <a href="${cp}/formularios/midias/listagem.jsp">
              Voltar
            </a>
          </td>
          <td class="alinharDireita">
            <input type="submit" value="Salvar"/>
          </td>
        </tr>
      </table>

    </form>

  </body>

</html>
