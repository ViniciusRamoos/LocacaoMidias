<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Alterar Midia</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
    <h1>Alterar Midia</h1>

    <form method="post" action="${cp}/processaMidias">

      <input name="acao" type="hidden" value="alterar"/>
      <input name="id" type="hidden" value="${requestScope.midia.id}"/>

      <table>
        <tr>
          <td class="alinharDireita">Titulo:</td>
          <td>
            <input name="titulo"
                   type="text"
                   size="20"
                   maxlength="100"
                   value="${requestScope.midia.titulo}"
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
                   value="${requestScope.midia.anoLancamento}"
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
                   value="${requestScope.midia.codigoBarras}"
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
                   value="${requestScope.midia.duracaoMinutos}"
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
                  <c:choose>
                      <c:when test="${requestScope.midia.atorPrincipal.id eq atorPrincipal.id}">
                          <option value="${atorPrincipal.id}" selected>
                            ${atorPrincipal.nome} ${atorPrincipal.sobrenome}
                          </option>
                      </c:when>
                      <c:otherwise>
                          <option value="${atorPrincipal.id}">
                            ${atorPrincipal.nome} ${atorPrincipal.sobrenome}
                          </option>
                      </c:otherwise>
                  </c:choose>
                
              </c:forEach>
            </select>

          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Ator Coadjuvante:</td>
          <td>
            <select name="idAtorCoadjuvante" required>
              <c:forEach items="${servicosAtor.todos}" var="atorCoadjuvante">
                  <c:choose>
                      <c:when test="${requestScope.midia.atorCoadjuvante.id eq atorCoadjuvante.id}">
                          <option value="${atorCoadjuvante.id}" selected>
                            ${atorCoadjuvante.nome} ${atorCoadjuvante.sobrenome}
                          </option>
                      </c:when>
                      <c:otherwise>
                          <option value="${atorCoadjuvante.id}">
                            ${atorCoadjuvante.nome} ${atorCoadjuvante.sobrenome}
                          </option>
                      </c:otherwise>
                  </c:choose>
                
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
                  <c:choose>
                      <c:when test="${requestScope.midia.genero.id eq genero.id}">
                          <option value="${genero.id}" selected>
                            ${genero.descricao}
                          </option>
                      </c:when>
                      <c:otherwise>
                          <option value="${genero.id}">
                            ${genero.descricao}
                          </option>
                      </c:otherwise>
                  </c:choose>
                
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
                  <c:choose>
                      <c:when test="${requestScope.midia.classificacaoEtaria.id eq CE.id}">
                          <option value="${CE.id}" selected>
                            ${CE.descricao}
                          </option>
                      </c:when>
                      <c:otherwise>
                          <option value="${CE.id}">
                            ${CE.descricao}
                          </option>
                      </c:otherwise>
                  </c:choose>
                
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
                  <c:choose>
                      <c:when test="${requestScope.midia.tipo.id eq tipo.id}">
                          <option value="${tipo.id}" selected>
                            ${tipo.descricao}
                          </option>
                      </c:when>
                      <c:otherwise>
                          <option value="${tipo.id}">
                            ${tipo.descricao}
                          </option>
                      </c:otherwise>
                  </c:choose>
                
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
                  <c:choose>
                      <c:when test="${requestScope.midia.classificacaoInterna.id eq CI.id}">
                          <option value="${CI.id}" selected>
                            ${CI.descricao}
                          </option>
                      </c:when>
                      <c:otherwise>
                          <option value="${CI.id}">
                            ${CI.descricao}
                          </option>
                      </c:otherwise>
                  </c:choose>
                
              </c:forEach>
            </select>

          </td>
        </tr>   
      </table>
        <div class="botao-container">
            <a href="${cp}/formularios/midias/listagem.jsp"> Voltar </a>
            <input type="submit" value="Salvar"/>
        </div>
    </form>
    </div>
  </body>

</html>
