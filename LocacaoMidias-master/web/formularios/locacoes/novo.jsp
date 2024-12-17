<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    
    <title>Nova Locação</title>
    
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
    
    <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
    <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>
    <script src="${cp}/js/formularios/locacoes/novo.js"></script>
    
  </head>

  <body>
    <div class="container">
    <h1>Nova Locação</h1>

    <form id="formNovaLocacao" method="post" action="${cp}/processaLocacoes">
      <input name="acao" type="hidden" value="inserir"/>
      <input id="hiddenItensLocacao" name="itensLocacao" type="hidden"/>

      <div id="divCliente">
        <jsp:useBean 
            id="servicosC" 
            scope="page" 
            class="locacaomidias.servicos.ClienteServices"/>

        Cliente:
        <br/>
        <select id="selectCliente" name="idCliente" required>
          <c:forEach items="${servicosC.todos}" var="cliente">
            <option value="${cliente.id}">
              ${cliente.nome} ${cliente.sobrenome}
            </option>
          </c:forEach>
        </select>
      </div>

      <div id="divItensLocacao">
        <table>
          <tr>
            <td>

              <jsp:useBean 
                  id="servicosExemplar" 
                  scope="page" 
                  class="locacaomidias.servicos.ExemplarServices"/>

              <p>
                Exemplar:
                <br/>
                <select id="selectExemplar">
                  <c:forEach items="${servicosExemplar.todos}" var="exemplar">
                      <c:choose>
                          <c:when test="${exemplar.disponivel}">
                              <option value="${exemplar.codigoInterno}"
                                      data-valor="${exemplar.midia.classificacaoInterna.valorAluguel}"
                                      data-titulo="${exemplar.midia.titulo}"
                                      data-descricao="${exemplar.midia.tipo.descricao}">
                                  ${exemplar.midia.titulo} (${exemplar.midia.tipo.descricao})
                              por ${exemplar.midia.classificacaoInterna.valorAluguel}</option>
                          </c:when>
                          <c:otherwise>
                              <option disabled>${exemplar.midia.titulo} (${exemplar.midia.tipo.descricao}</option>
                          </c:otherwise>
                      </c:choose>
                  </c:forEach>
                </select>
              </p>

              <p>
              Data de Retorno:
              <br/>
              <input id="dataFim"
                     name="dataFim"
                     type="date"
                     required/>
              </p>

            </td>
            <td class="btnsItensVenda">
              <p><input id="btnInserir" type="button" value="&#x2795;"/></p>
              <p><input id="btnRemover" type="button" value="&#x2796;"/></p>
              <p><input id="btnLimpar" type="button" value="&#x274C;"/></p>
            </td>
            <td>
              Itens da Locação:
              <br/>
              <select id="selectItensLocacao" size="10" multiple>
              </select>
              <br/>
              <div>
                <div id="divTotal">Total: R$ 0,00</div>
              </div>
            </td>
          </tr>
          <tr>
            <td>
            </td>
            <td></td>
          </tr>
        </table>
      </div>
      <div class="botao-container"> 
        <td class="alinharDireita">
              <input id="btnSalvar" type="submit" value="Salvar"/>
        </td>
        <a href="${cp}/formularios/locacoes/listagem.jsp">
          Voltar
        </a>
      </div>
    </form>
    </div>
    </div>  
    </body>

</html>
