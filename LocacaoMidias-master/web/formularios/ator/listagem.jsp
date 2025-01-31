<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="prefixo" value="processaAtores?acao=preparar"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Atores Cadastrados</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="container">
        <h1>Atores Cadastrados</h1>

        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Data Estreia</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean id="servicos" scope="page" class="locacaomidias.servicos.AtorServices" />
                <c:forEach items="${servicos.todos}" var="ator">
                    <tr>
                        <td>${ator.id}</td>
                        <td>${ator.nome} ${ator.sobrenome}</td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${ator.dataEstreia}" var="data" scope="page"/>${data}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${ator.id}">Alterar</a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${ator.id}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="botao-container">
            <a href="${cp}/formularios/ator/novo.jsp">Novo Ator</a>
            <a href="${cp}/index.jsp">Tela Principal</a>
        </div>
    </div>
</body>
</html>
